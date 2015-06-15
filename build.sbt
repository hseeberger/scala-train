// *****************************************************************************
// Projects
// *****************************************************************************

lazy val `scala-train` =
  project
    .in(file("."))
    .enablePlugins(AutomateHeaderPlugin, GitVersioning)
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        library.log4jApi,
        library.log4jCore,
        library.scalaCheck % Test,
        library.scalaTest  % Test
      )
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val log4j      = "2.10.0"
      val scalaCheck = "1.13.5"
      val scalaTest  = "3.0.4"
    }
    val log4jApi   = "org.apache.logging.log4j" %  "log4j-api"  % Version.log4j
    val log4jCore  = "org.apache.logging.log4j" %  "log4j-core" % Version.log4j
    val scalaCheck = "org.scalacheck"           %% "scalacheck" % Version.scalaCheck
    val scalaTest  = "org.scalatest"            %% "scalatest"  % Version.scalaTest
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  commonSettings ++
  gitSettings ++
  scalafmtSettings

lazy val commonSettings =
  Seq(
    // scalaVersion from .travis.yml via sbt-travisci
    // scalaVersion := "2.12.3",
    organization := "de.heikoseeberger",
    organizationName := "Heiko Seeberger",
    startYear := Some(2015),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8"
    ),
    Compile / unmanagedSourceDirectories := Seq((Compile / scalaSource).value),
    Test / unmanagedSourceDirectories := Seq((Test / scalaSource).value),
    initialCommands := "import de.heikoseeberger.scalatrain._"
)

lazy val gitSettings =
  Seq(
    git.useGitDescribe := true
  )

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true
  )
