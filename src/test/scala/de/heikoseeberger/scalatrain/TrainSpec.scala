package de.heikoseeberger.scalatrain

import org.scalatest.{ Matchers, WordSpec }

class TrainSpec extends WordSpec with Matchers {
  import TestData._

  "Creating a Train" should {
    "throw an IllegalArgumentException for an empty schedule" in {
      an[IllegalArgumentException] should be thrownBy Train(TrainInfo.RegionalExpress(666), Nil)
    }

    "throw an IllegalArgumentException for a schedule with one stop" in {
      an[IllegalArgumentException] should be thrownBy Train(
        TrainInfo.RegionalExpress(666),
        List(Stop(scalaCity, re666ScalaCity._1, re666ScalaCity._2))
      )
    }

    "throw an IllegalArgumentException for a schedule with duplicate Stations" in {
      an[IllegalArgumentException] should be thrownBy Train(
        TrainInfo.RegionalExpress(666),
        List(
          Stop(scalaCity, re666ScalaCity._1, re666ScalaCity._2),
          Stop(scalaCity, re666SlickMountain._1, re666SlickMountain._2)
        )
      )
    }

    "throw an IllegalArgumentException for a schedule not increasing in time" in {
      an[IllegalArgumentException] should be thrownBy Train(
        TrainInfo.RegionalExpress(86516),
        List(
          Stop(scalaCity, re666ScalaCity._1, re666ScalaCity._2),
          Stop(slickMountain, re666ScalaCity._2, re666SlickMountain._2)
        )
      )
    }
  }

  "Calling stations" should {
    "return the stations from the schedule" in {
      re666.stations shouldBe List(scalaCity, slickMountain)
    }
  }

  "Calling toString" should {
    "return the correct string" in {
      re666.toString shouldBe "RE 666"
      ice610.toString shouldBe "ICE 610"
      ic2024.toString shouldBe "IC 2024"
      ice610
        .copy(info = ice610.info.asInstanceOf[TrainInfo.InterCityExpress].copy(hasWifi = true))
        .toString shouldBe "ICE 610 (WIFI)"
    }
  }
}
