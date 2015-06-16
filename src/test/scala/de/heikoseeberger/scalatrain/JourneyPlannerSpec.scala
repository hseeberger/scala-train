package de.heikoseeberger.scalatrain

import org.scalatest.{ Matchers, WordSpec }

class JourneyPlannerSpec extends WordSpec with Matchers {
  import TestData._

  "Calling stations" should {
    "return the correct stations" in {
      journeyPlanner.stations shouldBe Set(
        scalaCity, playTown, slickMountain, newReactive, losSprayos, scalactica, akkapolis
      )
    }
  }

  "Calling trainsAt" should {
    "return the correct trains" in {
      journeyPlanner.trainsAt(scalaCity) shouldBe Set(re666)
      journeyPlanner.trainsAt(slickMountain) shouldBe Set(re666, ice610)
      journeyPlanner.trainsAt(losSprayos) shouldBe Set(ice610, ic2312)
      journeyPlanner.trainsAt(scalactica) shouldBe Set(ice610, ic2024, ic2312, ice1741)
      journeyPlanner.trainsAt(akkapolis) shouldBe Set(ic2024, ice1741)
    }
  }

  "Calling departuresAt" should {
    "return the correct departures" in {
      journeyPlanner.departuresAt(scalaCity) shouldBe Set(re666ScalaCity._2 -> re666)
      journeyPlanner.departuresAt(scalactica) shouldBe Set(
        ice610Scalactica._2 -> ice610,
        ic2024Scalactica._2 -> ic2024,
        ic2312Scalactica._2 -> ic2312,
        ice1741Scalactica._2 -> ice1741
      )
      journeyPlanner.departuresAt(akkapolis) shouldBe Set(ic2024Akkapolis._2 -> ic2024, ice1741Akkapolis._2 -> ice1741)
    }
  }

  "Calling isShortTrip" should {
    "return true if from and to are connected by a single train with no more than one intermediate station" in {
      journeyPlanner.isShortTrip(scalaCity, playTown) shouldBe true
      journeyPlanner.isShortTrip(playTown, newReactive) shouldBe true
      journeyPlanner.isShortTrip(scalaCity, newReactive) shouldBe false
      journeyPlanner.isShortTrip(scalaCity, scalactica) shouldBe false
    }
  }

  "Calling connections" should {
    "throw an IllegalArgumentException for equal from and to" in {
      an[IllegalArgumentException] should be thrownBy journeyPlanner.connections(scalaCity, scalaCity, Time())
    }

    "return two connections from Scala City to Akkapolis after 8:00" in {
      journeyPlanner.connections(scalaCity, akkapolis, Time(8)) shouldEqual Set(
        List(
          Leg(scalaCity, slickMountain, re666),
          Leg(slickMountain, scalactica, ice610),
          Leg(scalactica, akkapolis, ic2024)
        ),
        List(
          Leg(scalaCity, slickMountain, re666),
          Leg(slickMountain, losSprayos, ice610),
          Leg(losSprayos, scalactica, ic2312),
          Leg(scalactica, akkapolis, ice1741)
        ),
        List(
          Leg(scalaCity, slickMountain, re666),
          Leg(slickMountain, scalactica, ice610),
          Leg(scalactica, akkapolis, ice1741)
        )
      )
    }

    "return zero connections from Scala City to Akkapolis after 9:00" in {
      journeyPlanner.connections(scalaCity, akkapolis, Time(9)) shouldEqual Set.empty
    }
  }
}
