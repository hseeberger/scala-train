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
      re666.stations shouldBe List(scalaCity, playTown, slickMountain, newReactive)
    }
  }

  "Calling backToBackStations" should {
    "return the back-to-back stations from the schedule" in {
      re666.backToBackStations shouldEqual List(
        scalaCity -> playTown,
        playTown -> slickMountain,
        slickMountain -> newReactive
      )
    }
  }

  "Calling arrivalTimeByStation" should {
    "return the arrival times from the schedule" in {
      re666.arrivalTimeByStation shouldBe Map(
        scalaCity -> re666ScalaCity._1,
        playTown -> re666PlayTown._1,
        slickMountain -> re666SlickMountain._1,
        newReactive -> re666NewReactive._1
      )
    }
  }

  "Calling departureTimeByStation" should {
    "return the departure times from the schedule" in {
      re666.departureTimeByStation shouldBe Map(
        scalaCity -> re666ScalaCity._2,
        playTown -> re666PlayTown._2,
        slickMountain -> re666SlickMountain._2,
        newReactive -> re666NewReactive._2
      )
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
