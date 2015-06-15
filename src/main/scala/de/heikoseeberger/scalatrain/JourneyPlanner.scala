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

final class JourneyPlanner(trains: Set[Train]) extends Logging {

  logger.debug(f"Using these ${trains.size} trains:%n  ${trains.mkString(f"%n  ")}")

  def stations: Set[Station] =
    trains.flatMap(_.stations)

  def trainsAt(station: Station): Set[Train] =
    trains.filter(_.stations.contains(station))

  def departuresAt(station: Station): Set[(Time, Train)] =
    for {
      train <- trains
      stop  <- train.schedule if stop.station == station
    } yield (stop.departureTime, train)
}
