name := "sbt-dotenv"
organization := "nl.gn0s1s"
description := "An sbt plugin to load environment variables from .env into the JVM System Environment for local development. Assists with 'Twelve Factor App' development principle 3 'Store config in the environment'."
startYear := Some(2014)
homepage := Some(url("https://github.com/philippus/sbt-dotenv"))
licenses += ("MIT" -> url("http://opensource.org/licenses/MIT"))

developers := List(
  Developer(
    id = "Philippus",
    name = "Philippus Baalman",
    email = "",
    url = url("https://github.com/philippus")
  ),
  Developer(
    id = "mefellows",
    name = "Matt Fellows",
    email = "",
    url = url("http://www.onegeek.com.au")
  )
)

enablePlugins(SbtPlugin)
sbtPlugin := true
pluginCrossBuild / sbtVersion := "1.3.9" // minimum version we target because of using Native.load, see https://github.com/Philippus/sbt-dotenv/issues/81

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.11" % Test
)

enablePlugins(ScriptedPlugin)

scriptedLaunchOpts := {
  if (System.getProperty("java.version").startsWith("1.")) {
    scriptedLaunchOpts.value ++ Seq(
      "-Xmx1024M",
      "-Dplugin.version=" + version.value
    )
  } else {
    scriptedLaunchOpts.value ++ Seq(
      "--illegal-access=deny",
      "--add-opens",
      "java.base/java.util=ALL-UNNAMED",
      "--add-opens",
      "java.base/java.lang=ALL-UNNAMED",
      "-Xmx1024M",
      "-Dplugin.version=" + version.value
    )
  }
}

scriptedBufferLog := false
