package var4.task1

import scala.util.{Try, Success, Failure}
import java.io.{PrintWriter}
import scala.util.Using
import scala.annotation.tailrec
import java.io.File


def test(input: String, output:String) =
  Using.Manager {use =>
    val source = use(scala.io.Source.fromFile(input))
    val outputFile = new File(output)
    outputFile.getParentFile().mkdirs()
    val writer = use(new PrintWriter(outputFile))
    val lines = source.getLines()

    @tailrec def transform(iter: Iterator[String]): Unit =
      iter.nextOption() match
        case None => ()
        case Some(line) =>
          val grades = line.split(" ").tail.map(_.toDouble)
          if grades.sum / grades.length > 7 then
            writer.println(s"${line.split(" ").head.toUpperCase()} ${grades.mkString(" ")}")
          else
            writer.println(line)
          transform(iter) 
      
    transform(lines)
  } match
    case Failure(exception) => println(s"Exeption occured when working with file: ${exception.toString()}")
    case Success(_) => ()
  