package de.heikoseeberger.scalatrain

import org.scalatest.{ Matchers, WordSpec }

class PackageSpec extends WordSpec with Matchers {

  "Calling isIncreasing(Tailrec)" should {
    "return true for an empty sequence" in {
      isIncreasing(List.empty[Time]) shouldBe true
      isIncreasingTailrec(List.empty[Time]) shouldBe true
      isIncreasingTailrec(List.empty[Int]) shouldBe true
    }

    "return true for a sequence with one element" in {
      isIncreasing(List(Time())) shouldBe true
      isIncreasingTailrec(List(Time())) shouldBe true
      isIncreasingTailrec(List(1)) shouldBe true
    }

    "return true for an increasing sequence" in {
      isIncreasing(List(Time(1), Time(2), Time(3))) shouldBe true
      isIncreasingTailrec(List(Time(1), Time(2), Time(3))) shouldBe true
      isIncreasingTailrec(List(1, 2, 3)) shouldBe true
    }

    "return false for an non-increasing sequence" in {
      isIncreasing(List(Time(1), Time(2), Time(2), Time(3))) shouldBe false
      isIncreasingTailrec(List(Time(1), Time(2), Time(2), Time(3))) shouldBe false
      isIncreasingTailrec(List(1, 2, 2, 3)) shouldBe false
    }
  }
}
