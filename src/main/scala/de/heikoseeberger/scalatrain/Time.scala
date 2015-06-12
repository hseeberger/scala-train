package de.heikoseeberger.scalatrain

object Time {
  def fromMinutes(minutes: Int): Time = new Time(minutes / 60, minutes % 60)
}

case class Time(hours: Int = 0, minutes: Int = 0) {
  require(hours >= 0 && hours < 24, "hours must be within [0, 24)!")
  require(minutes >= 0 && minutes < 60, "minutes must be within [0, 60)!")

  def -(that: Time): Int = minus(that)

  def minus(that: Time): Int = {
    def asMinutes(time: Time) = time.hours * 60 + time.minutes
    asMinutes(this) - asMinutes(that)
  }
}
