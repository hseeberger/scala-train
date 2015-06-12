class Time(val hours: Int, val minutes: Int) {
  // TODO Check preconditions: hours must be within [0, 24)!
  // TODO Check preconditions: minutes must be within [0, 60)!

  def minus(that: Time): Int = {
    val thisAsMinutes = hours * 60 + minutes
    val thatAsMinutes = that.hours * 60 + that.minutes
    thisAsMinutes - thatAsMinutes
  }
}
