package de.heikoseeberger.scalatrain

import org.scalatest.{ Matchers, WordSpec }

class StopSpec extends WordSpec with Matchers {
  import TestData._

  "Creating a Stop" should {
    "throw an IllegalArgumentException if the arrivalTime is not before the departureTime" in {
      an[IllegalArgumentException] should be thrownBy Stop(scalaCity, Time(), Time())
    }
  }
}
