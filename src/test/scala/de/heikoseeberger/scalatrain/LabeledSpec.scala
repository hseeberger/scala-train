package de.heikoseeberger.scalatrain

import java.util.Locale
import org.scalatest.{ Matchers, WordSpec }

class LabeledSpec extends WordSpec with Matchers {
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
