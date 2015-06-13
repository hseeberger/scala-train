package de.heikoseeberger.scalatrain

class JourneyPlanner(trains: Set[Train]) {

  def stations: Set[Station] = trains.flatMap(_.stations)

  def trainsAt(station: Station): Set[Train] = trains.filter(_.stations.contains(station))
}
