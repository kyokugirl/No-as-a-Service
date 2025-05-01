val scala3Version = "3.6.4"

lazy val root = project
  .in(file("."))
  .settings(
    name := "No-as-a-Service",
    version := "0.1.0",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.1.17",
      "dev.zio" %% "zio-http" % "3.2.0",
      "dev.zio" %% "zio-json" % "0.7.42"
    )
  )
