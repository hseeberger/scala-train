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

import java.util.Locale
import org.scalatest.{ Matchers, WordSpec }

final class LabeledSpec extends WordSpec with Matchers {
  import TestData._

  "Calling label" should {
    "return the label from the resource bundle for known key and locale" in {
      implicit val locale = Locale.US
      Labeled.label(scalaCity) shouldBe "Scala City"
    }

    "return the key for an unknown key" in {
      implicit val locale = Locale.US
      Labeled.label(Station("unknown")) shouldBe "station.unknown"
    }
  }
}
