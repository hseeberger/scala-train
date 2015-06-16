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

import scala.collection.breakOut
import scala.collection.immutable.Seq

final case class Train(info: TrainInfo, schedule: Seq[Stop]) {
  require(schedule.size >= 2, "schedule must have at least two stops!")
  require(stations.distinct == stations, "schedule must not contain duplicate stations!")
  require(
    isIncreasing(schedule.flatMap(stop => List(stop.arrivalTime, stop.departureTime))),
    "schedule must be increasing in time!"
  )

  def stations: Seq[Station] =
    schedule.map(_.station)

  def backToBackStations: Seq[(Station, Station)] =
    stations.zip(stations.tail)

  def arrivalTimeByStation: Map[Station, Time] =
    schedule.map(stop => stop.station -> stop.arrivalTime)(breakOut)

  def departureTimeByStation: Map[Station, Time] =
    schedule.map(stop => stop.station -> stop.departureTime)(breakOut)

  override def toString =
    info match {
      case TrainInfo.InterCityExpress(number, true) => s"ICE $number (WIFI)"
      case TrainInfo.InterCityExpress(number, _)    => s"ICE $number"
      case TrainInfo.InterCity(number)              => s"IC $number"
      case TrainInfo.RegionalExpress(number)        => s"RE $number"
    }
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
  require(arrivalTime < departureTime, "arrivalTime must be before departureTime!")
}

final case class Station(name: String) {
  require(name.nonEmpty, "name must not be empty!")
}
