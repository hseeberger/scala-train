package de.heikoseeberger.scalatrain

object TestData {

  val scalaCity = Station("scalaCity")
  val slickMountain = Station("slickMountain")
  val losSprayos = Station("losSprayos")
  val scalactica = Station("scalactica")
  val akkapolis = Station("akkapolis")

  val re666ScalaCity = (Time(8, 16), Time(8, 17))
  val re666SlickMountain = (Time(9, 8), Time(9, 11))
  val re666 = Train(
    "RegionalExpress",
    666,
    List(
      scalaCity,
      slickMountain
    )
  )

  val ice610SlickMountain = (Time(10, 0), Time(10, 3))
  val ice610LosSprayos = (Time(12, 28), Time(12, 36))
  val ice610Scalactica = (Time(14, 5), Time(14, 10))
  val ice610 = Train(
    "InterCityExpress",
    610,
    List(
      slickMountain,
      losSprayos,
      scalactica
    )
  )

  val ic2024Scalactica = (Time(14, 5), Time(14, 10))
  val ic2024Akkapolis = (Time(14, 27), Time(14, 29))
  val ic2024 = Train(
    "InterCity",
    2024,
    List(
      scalactica,
      akkapolis
    )
  )

  val ic2312LosSprayos = (Time(12, 37), Time(12, 39))
  val ic2312Scalactica = (Time(15, 5), Time(15, 10))
  val ic2312 = Train(
    "InterCity",
    2312,
    List(
      losSprayos,
      scalactica
    )
  )

  val ice1741Scalactica = (Time(), Time(15, 10)) // Initial station
  val ice1741Akkapolis = (Time(15, 29), Time(15, 31))
  val ice1741 = Train(
    "InterCityExpress",
    1741,
    List(
      scalactica,
      akkapolis
    )
  )
}
