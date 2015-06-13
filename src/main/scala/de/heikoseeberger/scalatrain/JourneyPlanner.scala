package de.heikoseeberger.scalatrain

class JourneyPlanner(trains: Set[Train]) {
  def stations: Set[Station] = trains.flatMap(_.stations)
}
