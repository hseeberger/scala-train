package de.heikoseeberger.scalatrain

class Train(val kind: String, val number: Int) {
  require(kind.nonEmpty, "kind must not be empty!")
}
