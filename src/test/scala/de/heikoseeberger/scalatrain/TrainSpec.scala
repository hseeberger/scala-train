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

final class TrainSpec extends WordSpec with Matchers {
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
