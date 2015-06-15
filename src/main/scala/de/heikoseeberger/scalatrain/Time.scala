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

object Time {

  def fromMinutes(minutes: Int): Time =
    new Time(minutes / 60, minutes % 60)

  def isIncreasing(times: Seq[Time]): Boolean =
    times
      .sliding(2)
      .forall(times => times.size < 2 || times.head < times.last)
}

final case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {
  require(hours >= 0 && hours < 24, "hours must be within [0, 24)!")
  require(minutes >= 0 && minutes < 60, "minutes must be within [0, 60)!")

  def -(that: Time): Int =
    minus(that)

  def minus(that: Time): Int = {
    def asMinutes(time: Time) = time.hours * 60 + time.minutes
    asMinutes(this) - asMinutes(that)
  }

  override def toString = f"$hours%02d:$minutes%02d"

  override def compare(that: Time) = this - that
}
