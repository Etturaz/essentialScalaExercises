import Dependencies._

ThisBuild / scalaVersion := "2.13.0"
ThisBuild / version := "0.1"
ThisBuild / organization := "com.github"
ThisBuild / organizationName := "Etturaz"

val scalaTestVersion = "3.0.8"
val semanticdbVersion = "4.2.3"

lazy val root = (project in file("."))
  .settings(
    name := "EssentialScala",
    libraryDependencies ++= Seq(
      scalaTest % scalaTestVersion % Test
    ),
    addCompilerPlugin(
      "org.scalameta" % "semanticdb-scalac" % semanticdbVersion cross CrossVersion.full
    ),
    scalacOptions ++= Seq("-Yrangepos", "-unchecked", "-deprecation", "-feature")
  )
