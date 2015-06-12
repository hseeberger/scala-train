organization := "de.heikoseeberger"
name         := "scala-train"
version      := "1.0.8"

scalaVersion   := "2.11.8"
scalacOptions ++= Vector(
  "-unchecked",
  "-deprecation",
  "-target:jvm-1.8"
)

import scalariform.formatter.preferences._
scalariformPreferences := scalariformPreferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
  .setPreference(DoubleIndentClassDeclaration, true)

initialCommands := "import de.heikoseeberger.scalatrain._"
