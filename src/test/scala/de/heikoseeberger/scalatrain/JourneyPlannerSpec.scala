package de.heikoseeberger.scalatrain

import org.scalatest.{ Matchers, WordSpec }

class JourneyPlannerSpec extends WordSpec with Matchers {
  import TestData._

  "Calling stations" should {
    "return the correct stations" in {
      journeyPlanner.stations shouldBe Set(scalaCity, slickMountain, losSprayos, scalactica, akkapolis)
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
}
