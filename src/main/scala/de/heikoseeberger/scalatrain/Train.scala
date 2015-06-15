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

final case class Train(info: TrainInfo, schedule: Seq[Stop]) {
  require(schedule.size >= 2, "schedule must have at least two stops!")
  require(stations.distinct == stations, "schedule must not contain duplicate stations!")
  // TODO Check precondition: schedule must be increasing in time!

  def stations: Seq[Station] =
    schedule.map(_.station)
}

sealed abstract class TrainInfo {
  def number: Int
}

object TrainInfo {
  case class InterCityExpress(number: Int, hasWifi: Boolean = false) extends TrainInfo
  case class InterCity(number: Int)                                  extends TrainInfo
  case class RegionalExpress(number: Int)                            extends TrainInfo
}

final case class Stop(station: Station, arrivalTime: Time, departureTime: Time) {
  // TODO Check precondition: arrivalTime must be before departureTime!
}

final case class Station(name: String) {
  require(name.nonEmpty, "name must not be empty!")
}
