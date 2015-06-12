package de.heikoseeberger.scalatrain

object Time {
  def fromMinutes(minutes: Int): Time = new Time(minutes / 60, minutes % 60)
}

class Time(val hours: Int = 0, val minutes: Int = 0) {
  // TODO Check preconditions: hours must be within [0, 24)!
  // TODO Check preconditions: minutes must be within [0, 60)!

  def -(that: Time): Int = minus(that)

  def minus(that: Time): Int = {
    def asMinutes(time: Time) = time.hours * 60 + time.minutes
    asMinutes(this) - asMinutes(that)
  }
}
