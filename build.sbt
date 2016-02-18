import CommonDependency.dependencies

lazy val root = (project in file("."))
  .settings(
    name := "akka-util",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.11.7",
    libraryDependencies ++= dependencies.akka,
    libraryDependencies ++= dependencies.redis,
    libraryDependencies ++= dependencies.hbase_CDH5
  )
