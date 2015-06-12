package de.heikoseeberger.scalatrain

case class Train(kind: String, number: Int) {
  require(kind.nonEmpty, "kind must not be empty!")
}
