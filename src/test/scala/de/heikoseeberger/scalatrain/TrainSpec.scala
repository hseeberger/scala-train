package de.heikoseeberger.scalatrain

import org.scalatest.{ Matchers, WordSpec }

class TrainSpec extends WordSpec with Matchers {

  "Creating a Train" should {
    "throw an IllegalArgumentException for an empty kind" in {
      an[IllegalArgumentException] should be thrownBy Train("", 666)
    }
  }
}
