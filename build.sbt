ThisBuild / scalaVersion := "3.2.2"
ThisBuild / organization := "ru.bmstu"

lazy val general = (project in file("general"))
  .settings(
    name := "General"
  )

lazy val lab1 = (project in file("lab1"))
  .settings(
    name := "Lab 1",
    assembly / assemblyJarName := "lab1.jar",
    assembly / target := file("exec/")
  )
  .dependsOn(general)

lazy val lab2 = (project in file("lab2"))
  .settings(
    name := "Lab 2",
    assembly / assemblyJarName := "lab2.jar",
    assembly / target := file("exec/")
  )
  .dependsOn(general)