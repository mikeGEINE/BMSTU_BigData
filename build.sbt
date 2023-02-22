ThisBuild / scalaVersion := "3.2.2"
ThisBuild / organization := "com.example"
// ThisBuild / assemblyOutputPath in assembly := file(s"exec/${(assembly/assemblyJarName).value}") 
// Global / assembly / target := file("exec/")

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