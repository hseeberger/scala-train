organization := "de.heikoseeberger"
name         := "scala-train"
version      := "1.0.7"

scalaVersion   := "2.11.8"
scalacOptions ++= Vector(
  "-unchecked",
  "-deprecation",
  "-target:jvm-1.8"
)

libraryDependencies ++= Vector(
  "org.apache.logging.log4j" %  "log4j-api"  % "2.5",
  "org.apache.logging.log4j" %  "log4j-core" % "2.5",
  "org.scalactic"            %% "scalactic"  % "2.2.6",
  "org.scalacheck"           %% "scalacheck" % "1.12.5" % "test",
  "org.scalatest"            %% "scalatest"  % "2.2.6"  % "test"
)

import scalariform.formatter.preferences._
scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
  .setPreference(DoubleIndentClassDeclaration, true)

initialCommands := "import de.heikoseeberger.scalatrain._"

test.in(Test)         := { scalastyle.in(Compile).toTask("").value; test.in(Test).value }
scalastyleFailOnError := true

coverageMinimum          := 100
coverageFailOnMinimum    := true
coverageExcludedPackages := ".*App;.*Logger"
