class Time(val hours: Int, val minutes: Int) {
  // TODO Check preconditions: hours must be within [0, 24)!
  // TODO Check preconditions: minutes must be within [0, 60)!

  def minus(that: Time): Int = {
    def asMinutes(time: Time) = time.hours * 60 + time.minutes
    asMinutes(this) - asMinutes(that)
  }
}
