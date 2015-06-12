organization := "de.heikoseeberger"
name         := "scala-train"
version      := "1.0.1"

scalaVersion   := "2.11.7"
scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-target:jvm-1.8"
)

libraryDependencies ++= List(
  "org.scalacheck" %% "scalacheck" % "1.12.5" % "test",
  "org.scalatest"  %% "scalatest"  % "2.2.5"  % "test"
)

import scalariform.formatter.preferences._
scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
  .setPreference(DoubleIndentClassDeclaration, true)

initialCommands := "import de.heikoseeberger.scalatrain._"

test.in(Test)         := { scalastyle.in(Compile).toTask("").value; test.in(Test).value }
scalastyleFailOnError := true

coverageMinimum       := 100
coverageFailOnMinimum := true
