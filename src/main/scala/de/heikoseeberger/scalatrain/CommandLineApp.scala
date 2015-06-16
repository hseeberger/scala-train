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

import java.util.Locale
import org.scalactic.{ Accumulation, Bad, Every, Good, One }
import scala.collection.immutable.Seq

object CommandLineApp {
  import Labeled._
  import TestData._

  private implicit val locale: Locale = Locale.US

  def main(args: Array[String]): Unit =
    Accumulation
      .withGood(from(args), to(args), departureTime(args))(journeyPlanner.connections)
      .fold(printConnections, printErrors)

  private def printConnections(connections: Set[Seq[Leg]]) = {
    def formatConnection(connection: Seq[Leg]) = {
      val from          = label(connection.head.from)
      val departureTime = connection.head.train.departureTimeByStation(connection.head.from)
      val to            = label(connection.last.to)
      val arrivalTime   = connection.last.train.arrivalTimeByStation(connection.last.to)
      val trains        = connection.map(_.train).mkString(f"%n  ")
      f"Departure from $from at $departureTime, arrival at $to at $arrivalTime. Using these trains:%n  $trains"
    }
    connections.toList
      .sortBy(c => c.last.train.arrivalTimeByStation(c.last.to))
      .foreach(formatConnection)
  }

  private def printErrors(errors: Every[String]) = {
    val messages = errors.mkString(f"%n  ")
    println(f"Please correct the following errors:%n  $messages")
  }

  private def from(args: Array[String]) =
    if (args.length < 1)
      Bad(One("Missing argument for from-station!"))
    else {
      val station = Station(args.head)
      if (!journeyPlanner.stations.contains(station))
        Bad(One(s"Unknown from-station ${station.name}"))
      else
        Good(station)
    }

  private def to(args: Array[String]) =
    if (args.length < 2)
      Bad(One("Missing argument for to-station!"))
    else {
      val station = Station(args(1))
      if (!journeyPlanner.stations.contains(station))
        Bad(One(s"Unknown to-station ${station.name}"))
      else
        Good(station)
    }

  private def departureTime(args: Array[String]) =
    if (args.length < 3)
      Bad(One("Missing argument for departure time!"))
    else
      try Good(Time(args(2)))
      catch {
        case e: IllegalArgumentException => Bad(One(e.getMessage))
      }
}
