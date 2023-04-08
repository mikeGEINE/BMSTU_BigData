package var4.task2

import scala.util.{Try, Success, Failure}
import java.io.{PrintWriter}
import scala.util.Using
import scala.annotation.tailrec
import java.io.File
import scala.io.StdIn


def test(input: String, output:String) =
   
  Using.Manager {use =>
    val source = use(scala.io.Source.fromFile(input))
    val outputFile = new File(output)
    outputFile.getParentFile().mkdirs()
    val writer = use(new PrintWriter(outputFile))

    val body = source.mkString("").split("\\s+")

    println("What type of data are you looking for?")
    StdIn.readLine() match
      case "char" | "Char" => writer.println(body.filter(_.matches("\\D")).mkString(" "))
      case "word" | "Word" => writer.println(body.filter(_.matches("[a-zA-Z]+")).mkString(" "))
      case "int" | "Int" => writer.println(body.filter(_.matches("-?\\d+")).mkString(" "))
      case "float" | "Float" => writer.println(body.filter(_.matches("-?\\d+\\.\\d+")).mkString(" "))
      case _: String => println("Incorrect type!")
    
  } match
    case Failure(exception) => println(s"Exeption occured when working with file: ${exception.toString()}")
    case Success(_) => ()
  