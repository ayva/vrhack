enablePlugins(PlayScala)

name := "vrhackserver"
 
version := "1.0"

scalacOptions in ThisBuild ++= Seq("-feature", "-language:postfixOps", "-language:implicitConversions")

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.6.7",
  ws,
  filters,
  "com.softwaremill.macwire" %% "macros" % "2.2.0" % Provided
)

      