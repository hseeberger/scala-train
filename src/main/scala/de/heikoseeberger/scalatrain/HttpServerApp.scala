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

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.headers.`Accept-Language`
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.server.directives.HeaderMagnet
import akka.http.scaladsl.unmarshalling.Unmarshaller
import akka.stream.ActorMaterializer
import java.util.Locale
import scala.util.{ Failure, Success }

object HttpServerApp {
  import TestData._

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val mat    = ActorMaterializer()
    val log             = Logging(system, getClass.getName)

    import system.dispatcher
    Http().bindAndHandle(route, "127.0.0.1", 8000).onComplete {
      case Success(ServerBinding(address)) =>
        log.info("Listening on {}", address)
      case Failure(cause) =>
        log.error(cause, "Can't bind to 127.0.0.1:8000!")
        system.terminate()
    }
  }

  private def route = {
    import Directives._
    import JsonProtocol._
    import SprayJsonSupport._

    def stations =
      path("stations") {
        get {
          locale() { implicit locale =>
            complete {
              journeyPlanner.stations
            }
          }
        }
      }

    def connections =
      path("connections") {
        get {
          locale() { implicit locale =>
            implicit val parameterToStation = Unmarshaller.strict(Station.apply)
            implicit val parameterToTime    = Unmarshaller.strict(Time.apply)
            parameters("from".as[Station], "to".as[Station], "departureTime".as[Time]) {
              (from, to, departureTime) =>
                complete {
                  journeyPlanner.connections(from, to, departureTime)
                }
            }
          }
        }
      }

    stations ~ connections
  }

  private def locale(magnet: HeaderMagnet[`Accept-Language`]) = {
    def locale(acceptLanguage: Option[`Accept-Language`]) =
      acceptLanguage
        .flatMap(_.languages.headOption.map(range => Locale.forLanguageTag(range.primaryTag)))
        .getOrElse(Locale.US)
    Directives.optionalHeaderValueByType(magnet).map(locale)
  }
}
