package de.heikoseeberger.scalatrain

import org.scalatest.{ Matchers, WordSpec }

class JourneyPlannerSpec extends WordSpec with Matchers {
  import TestData._

  "Calling stations" should {
    "return the correct stations" in {
      journeyPlanner.stations shouldBe Set(scalaCity, slickMountain, losSprayos, scalactica, akkapolis)
    }
  }
}
