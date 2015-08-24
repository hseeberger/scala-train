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

import java.util.{ Locale, MissingResourceException, ResourceBundle }

object Labeled {

  final implicit object StationLabeled extends Labeled[Station] {
    override def label(station: Station)(implicit locale: Locale) =
      lookupLabel(s"station.${station.name}")
  }

  def label[A](a: A)(implicit labeled: Labeled[A], locale: Locale): String =
    labeled.label(a)

  private def lookupLabel(key: String)(implicit locale: Locale) =
    try ResourceBundle.getBundle("labels", locale).getString(key)
    catch {
      case _: MissingResourceException => key
    }
}

trait Labeled[A] {
  def label(a: A)(implicit locale: Locale): String
}
