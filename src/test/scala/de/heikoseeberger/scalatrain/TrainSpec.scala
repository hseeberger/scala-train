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
          scalaCity,
          slickMountain
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
        List(scalaCity)
      )
    }

    "throw an IllegalArgumentException for a schedule with duplicate Stations" in {
      an[IllegalArgumentException] should be thrownBy Train(
        "RegionalExpress",
        666,
        List(scalaCity, scalaCity)
      )
    }
  }
}
