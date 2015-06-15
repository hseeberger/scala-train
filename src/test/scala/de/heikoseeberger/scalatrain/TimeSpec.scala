package de.heikoseeberger.scalatrain

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{ Matchers, WordSpec }

class TimeSpec extends WordSpec with Matchers with GeneratorDrivenPropertyChecks {

  "IntOps" should {
    "implicitly extend Int providing a :: operator to create a Time" in {
      import Time._
      1 :: 10 shouldBe Time(1, 10)
    }
  }

  "Calling apply" should {
    "throw an IllegalArgumentException for an invalid String representation" in {
      an[IllegalArgumentException] should be thrownBy ("1:100": Time)
    }

    "implicitly convert a String into a Time" in {
      ("01:05": Time) shouldBe Time(1, 5)
    }
  }

  "Calling fromMinutes" should {
    "return Time(0, 10) for 10 minutes" in {
      Time.fromMinutes(10) shouldBe Time(0, 10)
    }

    "return Time(1, 10) for 70 minutes" in {
      Time.fromMinutes(70) shouldBe Time(1, 10)
    }

    "return a correct Time for minutes within [0, 1440)" in {
      forAll(Gen.choose(0, 1440 - 1) -> "minutes") { minutes =>
        val time = Time.fromMinutes(minutes)
        time.hours shouldBe minutes / 60
        time.minutes shouldBe minutes % 60
      }
    }
  }

  "Creating a Time" should {
    "throw an IllegalArgumentException for hours less than 0 or greater equal 24" in {
      forAll("hours") { (hours: Int) =>
        whenever(hours < 0 || hours >= 24) {
          an[IllegalArgumentException] should be thrownBy Time(hours)
        }
      }
    }

    "throw an IllegalArgumentException for minutes less than 0 or greater equal 60" in {
      forAll("minutes") { (minutes: Int) =>
        whenever(minutes < 0 || minutes >= 60) {
          an[IllegalArgumentException] should be thrownBy Time(minutes = minutes)
        }
      }
    }
  }

  "Calling minus or -" should {
    "return 0 for equal Times" in {
      Time(1, 10) - Time(1, 10) shouldBe 0
    }

    "return 60 for Time(1, 10) and Time(0, 10)" in {
      Time(1, 10).minus(Time(0, 10)) shouldBe 60
    }

    "return a correct value for any two Times" in {
      forAll(timeGen -> "time1", timeGen -> "time2") { (time1, time2) =>
        def asMinutes(time: Time) = time.hours * 60 + time.minutes
        time1 - time2 shouldBe asMinutes(time1) - asMinutes(time2)
      }
    }
  }

  "Calling toString" should {
    "return 09:10 for Time(9, 10)" in {
      Time(1, 10).toString shouldBe "01:10"
    }

    "return a correctly formatted string for any Time" in {
      forAll(timeGen -> "time") { time =>
        time.toString shouldBe f"${time.hours}%02d:${time.minutes}%02d"
      }
    }
  }

  "Calling the less than operator" should {
    "return false for Time(1, 10) < Time(1, 10)" in {
      Time(1, 10) < Time(0, 10) shouldBe false
    }
  }

  def timeGen = for {
    hours <- Gen.choose(0, 23)
    minutes <- Gen.choose(0, 59)
  } yield Time(hours, minutes)
}
