/*
 * Copyright 2015 Heiko Seeberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.heikoseeberger.scalatrain

import org.scalatest.{ Matchers, WordSpec }

final class JourneyPlannerSpec extends WordSpec with Matchers {
  import TestData._

  "Calling stations" should {
    "return the correct stations" in {
      journeyPlanner.stations shouldBe Set(scalaCity,
                                           slickMountain,
                                           losSprayos,
                                           scalactica,
                                           akkapolis)
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
        ice610Scalactica._2  -> ice610,
        ic2024Scalactica._2  -> ic2024,
        ic2312Scalactica._2  -> ic2312,
        ice1741Scalactica._2 -> ice1741
      )
      journeyPlanner.departuresAt(akkapolis) shouldBe Set(ic2024Akkapolis._2  -> ic2024,
                                                          ice1741Akkapolis._2 -> ice1741)
    }
  }
}
