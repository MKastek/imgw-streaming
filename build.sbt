ThisBuild / version := "1.0.0"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "imgw-streaming"
  )

val http4sVersion = "0.23.17"
val http4sNettyVersion = "0.5.4"
val circeVersion = "0.14.3"
val log4catsVersion = "2.5.0"
val logbackVersion = "1.2.11"
val cirisVersion = "3.0.0"
val nettyHTTP2CodecVersion = "4.1.85.Final"//version should be consistent with other netty packages coming from http4s
val javaxXmlVersion = "2.3.1"




libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % "1.0.0-M23",
  "org.http4s" %% "http4s-blaze-client" % "1.0.0-M23",
  "org.http4s" %% "http4s-circe" %"1.0.0-M23",
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "is.cir" %% "ciris" % cirisVersion,
  "is.cir" %% "ciris-refined" % cirisVersion,
  "io.netty" % "netty-codec-http2" % nettyHTTP2CodecVersion,
  "org.typelevel" %% "log4cats-slf4j" % log4catsVersion,
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "javax.xml.bind" % "jaxb-api" % javaxXmlVersion
)
