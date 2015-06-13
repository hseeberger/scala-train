package de.heikoseeberger.scalatrain

import org.scalatest.{ Matchers, WordSpec }

class TrainSpec extends WordSpec with Matchers {
  import TestData._

  "Creating a Train" should {
    "throw an IllegalArgumentException for an empty kind" in {
      an[IllegalArgumentException] should be thrownBy Train(
        "",
        666,
        List(
          Stop(scalaCity, re666ScalaCity._1, re666ScalaCity._2),
          Stop(slickMountain, re666SlickMountain._1, re666SlickMountain._2)
        )
      )
    }

    "throw an IllegalArgumentException for an empty schedule" in {
      an[IllegalArgumentException] should be thrownBy Train("RegionalExpress", 666, Nil)
    }

    "throw an IllegalArgumentException for a schedule with one stop" in {
      an[IllegalArgumentException] should be thrownBy Train(
        "RegionalExpress",
        666,
        List(Stop(scalaCity, re666ScalaCity._1, re666ScalaCity._2))
      )
    }

    "throw an IllegalArgumentException for a schedule with duplicate Stations" in {
      an[IllegalArgumentException] should be thrownBy Train(
        "RegionalExpress",
        666,
        List(
          Stop(scalaCity, re666ScalaCity._1, re666ScalaCity._2),
          Stop(scalaCity, re666SlickMountain._1, re666SlickMountain._2)
        )
      )
    }
  }

  "Calling stations" should {
    "return the stations from the schedule" in {
      re666.stations shouldBe List(scalaCity, slickMountain)
    }
  }
}
