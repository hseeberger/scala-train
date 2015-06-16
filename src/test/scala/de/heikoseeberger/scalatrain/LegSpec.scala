package de.heikoseeberger.scalatrain

import org.scalatest.{ Matchers, WordSpec }

class LegSpec extends WordSpec with Matchers {
  import TestData._

  "Creating a Leg" should {
    "throw an IllegalArgumentException for equal from and to" in {
      an[IllegalArgumentException] should be thrownBy Leg(scalaCity, scalaCity, re666)
    }

    "throw an IllegalArgumentException for from and to not in-order stations of the train" in {
      an[IllegalArgumentException] should be thrownBy Leg(playTown, scalaCity, re666)
      an[IllegalArgumentException] should be thrownBy Leg(scalaCity, akkapolis, re666)
    }
  }
}
