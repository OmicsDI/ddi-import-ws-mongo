name := "import-mongo-api"

version := "0.1"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "com.softwaremill.sttp.client" %% "core" % "2.0.0-RC3",
  "org.mongodb" % "bson" % "2.3",
  "io.spray" %% "spray-json" % "1.3.2",
  "net.liftweb" %% "lift-json" % "3.4.0",
  /*"org.mongodb" %% "casbah" % "3.1.1",
  "com.novus" %% "salat" % "1.9.9",
  "com.novus" % "salat-core_2.9.3" % "1.9.7",
  "com.github.salat" %% "salat" % "1.11.2",
  "com.novus" % "salat-core_2.9.1" % "0.0.8-SNAPSHOT",*/
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.7.0"//,
  //"org.mongodb.scala" % "mongo-scala-driver_2.13" % "2.7.0"

)
