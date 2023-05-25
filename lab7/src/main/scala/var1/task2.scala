package var1.task2

import java.io.{FileNotFoundException, IOException, PrintWriter, File}
import scala.io.StdIn
import scala.util.{Try, Success, Failure, Using}
import scala.annotation.tailrec

def test(input: String, output:String): Unit =
  println("Enter insertion position: ")
  val pos = StdIn.readLine().toInt
  println("Enter insertion string: ")
  val str = StdIn.readLine()

  Using.Manager {use =>
    val source = use(scala.io.Source.fromFile(input))
    val outputFile = new File(output)
    outputFile.getParentFile().mkdirs()
    val writer = use(new PrintWriter(outputFile))
    val text = source.mkString

    val (pre, post) = text.splitAt(pos)
    val replacedText = s"$pre$str$post"
    writer.print(replacedText)
  } match
    case Failure(exception) => println(s"Exeption occured when working with file: ${exception.toString()}")
    case Success(_) => ()
