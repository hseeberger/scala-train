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

final class LegSpec extends WordSpec with Matchers {
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
