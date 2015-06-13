package de.heikoseeberger.scalatrain

import scala.collection.immutable.Seq

case class Train(kind: String, number: Int, schedule: Seq[Stop]) {
  require(kind.nonEmpty, "kind must not be empty!")
  require(schedule.size >= 2, "schedule must have at least two stops!")
  require(stations.distinct == stations, "schedule must not contain duplicate stations!")
  // TODO Check precondition: schedule must be increasing in time

  def stations: Seq[Station] = schedule.map(_.station)
}

case class Stop(station: Station, arrivalTime: Time, departureTime: Time) {
  // TODO Check precondition: arrivalTime must be before departureTime
}

case class Station(name: String) {
  require(name.nonEmpty, "name must not be empty!")
}
