package de.heikoseeberger.scalatrain

import scala.collection.immutable.Seq

case class Train(kind: String, number: Int, schedule: Seq[Station]) {
  require(kind.nonEmpty, "kind must not be empty!")
  require(schedule.size >= 2, "schedule must have at least two stops!")
  require(schedule.distinct == schedule, "schedule must not contain duplicate stations!")
}

case class Station(name: String) {
  require(name.nonEmpty, "name must not be empty!")
}
