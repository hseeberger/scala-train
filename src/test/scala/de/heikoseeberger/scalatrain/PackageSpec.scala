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

class PackageSpec extends WordSpec with Matchers {

  "Calling isIncreasing(Tailrec)" should {
    "return true for an empty sequence" in {
      isIncreasing(Nil) shouldBe true
      isIncreasingTailrec(Nil) shouldBe true
    }

    "return true for a sequence with one element" in {
      isIncreasing(List(Time())) shouldBe true
      isIncreasingTailrec(List(Time())) shouldBe true
    }

    "return true for an increasing sequence" in {
      isIncreasing(List(Time(1), Time(2), Time(3))) shouldBe true
      isIncreasingTailrec(List(Time(1), Time(2), Time(3))) shouldBe true
    }

    "return false for an non-increasing sequence" in {
      isIncreasing(List(Time(1), Time(2), Time(2), Time(3))) shouldBe false
      isIncreasingTailrec(List(Time(1), Time(2), Time(2), Time(3))) shouldBe false
    }
  }
}
