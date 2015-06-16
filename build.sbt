organization := "de.heikoseeberger"
name         := "scala-train"
version      := "1.0.0"

scalaVersion   := "2.11.7"
scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-target:jvm-1.8"
)

libraryDependencies ++= List(
  "com.typesafe.akka"        %% "akka-http-experimental"            % "1.0",
  "com.typesafe.akka"        %% "akka-http-spray-json-experimental" % "1.0",
  "org.apache.logging.log4j" %  "log4j-api"                         % "2.3",
  "org.apache.logging.log4j" %  "log4j-core"                        % "2.3",
  "org.scalactic"            %% "scalactic"                         % "2.2.5",
  "org.scalacheck"           %% "scalacheck"                        % "1.12.4" % "test",
  "org.scalatest"            %% "scalatest"                         % "2.2.5"  % "test"
)

import scalariform.formatter.preferences._
SbtScalariform.autoImport.preferences := SbtScalariform.autoImport.preferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
  .setPreference(DoubleIndentClassDeclaration, true)

initialCommands := "import de.heikoseeberger.scalatrain._"

test.in(Test)         := { scalastyle.in(Compile).toTask("").value; test.in(Test).value }
scalastyleFailOnError := true

coverageMinimum          := 100
coverageFailOnMinimum    := true
coverageExcludedPackages := ".*App;.*JsonProtocol;.*Logger"
