import CommonDependency.dependencies

organization in ThisBuild := "io.github.junheng.akka"

lazy val root = (project in file("."))
  .settings(
    name := "akka-utils",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-pickling" % "0.10.1" withSources()
    ),
    libraryDependencies ++= dependencies.akka,
    libraryDependencies ++= dependencies.redis,
    libraryDependencies ++= dependencies.hbase_CDH5
  )
