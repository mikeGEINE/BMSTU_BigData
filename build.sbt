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

lazy val lab3 = (project in file("lab3"))
  .settings(
    name := "Lab 3",
    assembly / assemblyJarName := "lab3.jar",
    assembly / target := file("exec/")
  )
  .dependsOn(general)

lazy val lab4 = (project in file("lab4"))
  .settings(
    name := "Lab 4",
    assembly / assemblyJarName := "lab4.jar",
    assembly / target := file("exec/"),
    libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.32.0"
  )