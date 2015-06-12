organization := "de.heikoseeberger"
name         := "scala-train"
version      := "1.0.8"

scalaVersion   := "2.11.8"
scalacOptions ++= Vector(
  "-unchecked",
  "-deprecation",
  "-target:jvm-1.8"
)

libraryDependencies ++= Vector(
  "org.scalacheck" %% "scalacheck" % "1.12.5" % "test",
  "org.scalatest"  %% "scalatest"  % "2.2.6"  % "test"
)

import scalariform.formatter.preferences._
scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
  .setPreference(DoubleIndentClassDeclaration, true)

initialCommands := "import de.heikoseeberger.scalatrain._"

coverageMinimum       := 100
coverageFailOnMinimum := true
