/*
 * Copyright 2015 Heiko Seeberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.heikoseeberger.scalatrain

import scala.collection.immutable.Seq

final class JourneyPlanner(trains: Set[Train]) extends Logging {

  logger.debug(f"Using these ${trains.size} trains:%n  ${trains.mkString(f"%n  ")}")

  def stations: Set[Station] =
    trains.flatMap(_.stations)

  def trainsAt(station: Station): Set[Train] =
    trains.filter(_.stations.contains(station))

  def departuresAt(station: Station): Set[(Time, Train)] =
    for {
      train                             <- trains
      Stop(`station`, _, departureTime) <- train.schedule
    } yield (departureTime, train)

  def isShortTrip(from: Station, to: Station): Boolean =
    trains.exists {
      _.stations.dropWhile(_ != from) match {
        case Seq(`from`, _, `to`, _*) => true
        case Seq(`from`, `to`, _*)    => true
        case _                        => false
      }
    }

  def connections(from: Station, to: Station, departureTime: Time): Set[Seq[Leg]] = {
    require(from != to, "from and to must not be equal!")

    def hops(station: Station, time: Time) =
      for {
        train           <- trains
        (`station`, to) <- train.backToBackStations
        if train.departureTimeByStation(station) >= time
      } yield (to, train)

    def loop(legs: Seq[Leg]): Set[Seq[Leg]] =
      if (legs.last.to == to)
        Set(legs)
      else
        hops(legs.last.to, legs.last.train.arrivalTimeByStation(legs.last.to))
          .filterNot { case (next, _) => legs.flatMap(_.stations).contains(next) }
          .flatMap {
            case (next, train) if train == legs.last.train =>
              loop(legs.init :+ legs.last.copy(to = next))
            case (next, train) => loop(legs :+ Leg(legs.last.to, next, train))
          }

    hops(from, departureTime).flatMap {
      case (next, train) => loop(Vector(Leg(from, next, train)))
    }
  }
}

final case class Leg(from: Station, to: Station, train: Train) {
  require(
    train.stations.dropWhile(_ != from).reverse.dropWhile(_ != to).size >= 2,
    "from and to must be in-order stations of train"
  )

  def stations: Seq[Station] =
    train.stations.dropWhile(_ != from).takeWhile(_ != to) :+ to
}
