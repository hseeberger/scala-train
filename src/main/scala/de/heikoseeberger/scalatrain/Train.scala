package de.heikoseeberger.scalatrain

import scala.collection.immutable.Seq

case class Train(info: TrainInfo, schedule: Seq[Stop]) {
  require(schedule.size >= 2, "schedule must have at least two stops!")
  require(stations.distinct == stations, "schedule must not contain duplicate stations!")
  require(
    isIncreasing(schedule.flatMap(stop => List(stop.arrivalTime, stop.departureTime))),
    "schedule must be increasing in time!"
  )

  def stations: Seq[Station] = schedule.map(_.station)

  override def toString = info match {
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
  case class InterCity(number: Int) extends TrainInfo
  case class RegionalExpress(number: Int) extends TrainInfo
}

case class Stop(station: Station, arrivalTime: Time, departureTime: Time) {
  require(arrivalTime < departureTime, "arrivalTime must be before departureTime!")
}

case class Station(name: String) {
  require(name.nonEmpty, "name must not be empty!")
}
