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
import spray.json.{
  DefaultJsonProtocol,
  DeserializationException,
  JsBoolean,
  JsNumber,
  JsObject,
  JsString,
  JsValue,
  RootJsonFormat
}

object JsonProtocol extends DefaultJsonProtocol {

  final implicit object TrainInfoFormat extends RootJsonFormat[TrainInfo] {
    import TrainInfo._

    override def write(info: TrainInfo) = {
      def json(kind: String, number: Int, hasWifi: Boolean = false) = JsObject(
        "kind"    -> JsString(kind),
        "number"  -> JsNumber(number),
        "hasWifi" -> JsBoolean(hasWifi)
      )
      info match {
        case InterCityExpress(number, hasWifi) => json("ICE", number, hasWifi)
        case InterCity(number)                 => json("IC", number)
        case RegionalExpress(number)           => json("RE", number)
      }
    }

    override def read(json: JsValue) =
      json.asJsObject.getFields("kind", "number", "hasWifi") match {
        case Seq(JsString("ICE"), JsNumber(number), JsBoolean(hasWifi)) =>
          InterCityExpress(number.toInt, hasWifi)
        case Seq(JsString("IC"), JsNumber(number), _) => InterCity(number.toInt)
        case Seq(JsString("RE"), JsNumber(number), _) => RegionalExpress(number.toInt)
        case _                                        => throw new DeserializationException("TrainInfo expected!")
      }
  }

  implicit val timeFormat: RootJsonFormat[Time] =
    jsonFormat2(Time.apply)

  private val defaultStationFormat = jsonFormat1(Station.apply)

  implicit def stationFormat(implicit locale: Locale): RootJsonFormat[Station] =
    new RootJsonFormat[Station] {
      override def write(station: Station) = {
        val json = defaultStationFormat.write(station).asJsObject
        json.copy(json.fields + ("label" -> JsString(Labeled.label(station))))
      }
      override def read(json: JsValue) = defaultStationFormat.read(json)
    }

  implicit def stopFormat(implicit locale: Locale): RootJsonFormat[Stop] =
    jsonFormat3(Stop.apply)

  implicit def trainFormat(implicit locale: Locale): RootJsonFormat[Train] =
    jsonFormat2(Train.apply)

  implicit def legFormat(implicit locale: Locale): RootJsonFormat[Leg] =
    jsonFormat3(Leg.apply)
}
