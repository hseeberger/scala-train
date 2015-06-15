package de.heikoseeberger.scalatrain

class JourneyPlanner(trains: Set[Train]) extends Logging {

  logger.debug(f"Using these ${trains.size} trains:%n  ${trains.mkString(f"%n  ")}")

  def stations: Set[Station] = trains.flatMap(_.stations)

  def trainsAt(station: Station): Set[Train] = trains.filter(_.stations.contains(station))

  def departuresAt(station: Station): Set[(Time, Train)] = for {
    train <- trains
    stop <- train.schedule if stop.station == station
  } yield (stop.departureTime, train)

  def isShortTrip(from: Station, to: Station): Boolean = trains.exists {
    _.stations.dropWhile(_ != from) match {
      case Seq(`from`, _, `to`, _*) => true
      case Seq(`from`, `to`, _*)    => true
      case _                        => false
    }
  }
}
