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
      Stop(scalaCity, re666ScalaCity._1, re666ScalaCity._2),
      Stop(slickMountain, re666SlickMountain._1, re666SlickMountain._2)
    )
  )

  val ice610SlickMountain = (Time(10, 0), Time(10, 3))
  val ice610LosSprayos = (Time(12, 28), Time(12, 36))
  val ice610Scalactica = (Time(14, 5), Time(14, 10))
  val ice610 = Train(
    "InterCityExpress",
    610,
    List(
      Stop(slickMountain, ice610SlickMountain._1, ice610SlickMountain._2),
      Stop(losSprayos, ice610LosSprayos._1, ice610LosSprayos._2),
      Stop(scalactica, ice610Scalactica._1, ice610Scalactica._2)
    )
  )

  val ic2024Scalactica = (Time(14, 5), Time(14, 10))
  val ic2024Akkapolis = (Time(14, 27), Time(14, 29))
  val ic2024 = Train(
    "InterCity",
    2024,
    List(
      Stop(scalactica, ic2024Scalactica._1, ic2024Scalactica._2),
      Stop(akkapolis, ic2024Akkapolis._1, ic2024Akkapolis._2)
    )
  )

  val ic2312LosSprayos = (Time(12, 37), Time(12, 39))
  val ic2312Scalactica = (Time(15, 5), Time(15, 10))
  val ic2312 = Train(
    "InterCity",
    2312,
    List(
      Stop(losSprayos, ic2312LosSprayos._1, ic2312LosSprayos._2),
      Stop(scalactica, ic2312Scalactica._1, ic2312Scalactica._2)
    )
  )

  val ice1741Scalactica = (Time(), Time(15, 10)) // Initial station
  val ice1741Akkapolis = (Time(15, 29), Time(15, 31))
  val ice1741 = Train(
    "InterCityExpress",
    1741,
    List(
      Stop(scalactica, ice1741Scalactica._1, ice1741Scalactica._2),
      Stop(akkapolis, ice1741Akkapolis._1, ice1741Akkapolis._2)
    )
  )
}
