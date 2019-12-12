name := "import-mongo-api"

version := "0.1"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "com.softwaremill.sttp.client" %% "core" % "2.0.0-RC3",
  "io.spray" %% "spray-json" % "1.3.2",
  "net.liftweb" %% "lift-json" % "3.4.0",
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.7.0",
  "com.typesafe" % "config" % "1.3.4"
)
