organization := "de.heikoseeberger"
name         := "scala-train"
version      := "1.0.2"

scalaVersion   := "2.11.7"
scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-target:jvm-1.8"
)

libraryDependencies ++= List(
  "com.typesafe.akka"        %% "akka-http-experimental"            % "2.0.1",
  "com.typesafe.akka"        %% "akka-http-spray-json-experimental" % "2.0.1",
  "org.apache.logging.log4j" %  "log4j-api"                         % "2.5",
  "org.apache.logging.log4j" %  "log4j-core"                        % "2.5",
  "org.scalactic"            %% "scalactic"                         % "2.2.5",
  "org.scalacheck"           %% "scalacheck"                        % "1.12.5" % "test",
  "org.scalatest"            %% "scalatest"                         % "2.2.6"  % "test"
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
coverageExcludedPackages := ".*App;.*JsonProtocol;.*Logger"
