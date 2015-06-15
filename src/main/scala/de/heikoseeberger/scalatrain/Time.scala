package de.heikoseeberger.scalatrain

import scala.collection.immutable.Seq

object Time {
  def fromMinutes(minutes: Int): Time = new Time(minutes / 60, minutes % 60)

  def isIncreasing(times: Seq[Time]): Boolean = times
    .sliding(2)
    .forall {
      case Seq(t1, t2) => t1 < t2
      case _           => true
    }
}

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {
  require(hours >= 0 && hours < 24, "hours must be within [0, 24)!")
  require(minutes >= 0 && minutes < 60, "minutes must be within [0, 60)!")

  def -(that: Time): Int = minus(that)

  def minus(that: Time): Int = {
    def asMinutes(time: Time) = time.hours * 60 + time.minutes
    asMinutes(this) - asMinutes(that)
  }

  override def toString = f"$hours%02d:$minutes%02d"

  override def compare(that: Time) = this - that
}
