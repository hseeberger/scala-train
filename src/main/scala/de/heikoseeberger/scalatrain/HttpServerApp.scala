package de.heikoseeberger.scalatrain

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.headers.`Accept-Language`
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.server.directives.HeaderMagnet
import akka.http.scaladsl.unmarshalling.Unmarshaller
import akka.stream.ActorMaterializer
import java.util.Locale

object HttpServerApp {
  import TestData._

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val mat = ActorMaterializer()
    Http().bindAndHandle(route, "127.0.0.1", 8000) // scalastyle:off
  }

  private def route = {
    import Directives._
    import JsonProtocol._
    import SprayJsonSupport._

    val stations = path("stations") {
      get {
        locale() { implicit locale =>
          complete {
            journeyPlanner.stations
          }
        }
      }
    }

    val connections = path("connections") {
      get {
        locale() { implicit locale =>
          implicit val parameterToStation = Unmarshaller.strict(Station.apply)
          implicit val parameterToTime = Unmarshaller.strict(Time.apply)
          parameters('from.as[Station], 'to.as[Station], 'departureTime.as[Time]) { (from, to, departureTime) =>
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
    def locale(acceptLanguage: Option[`Accept-Language`]) = acceptLanguage
      .flatMap(_.languages.headOption.map(range => Locale.forLanguageTag(range.primaryTag)))
      .getOrElse(Locale.US)
    Directives.optionalHeaderValueByType(magnet).map(locale)
  }
}
