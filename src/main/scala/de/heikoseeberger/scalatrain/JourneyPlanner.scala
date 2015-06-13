package de.heikoseeberger.scalatrain

class JourneyPlanner(trains: Set[Train]) {

  def stations: Set[Station] = trains.flatMap(_.stations)

  def trainsAt(station: Station): Set[Train] = trains.filter(_.stations.contains(station))

  def departuresAt(station: Station): Set[(Time, Train)] = for {
    train <- trains
    stop <- train.schedule if stop.station == station
  } yield (stop.departureTime, train)
}
