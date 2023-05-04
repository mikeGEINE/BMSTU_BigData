package var3.task1

import java.io.{FileNotFoundException, IOException, PrintWriter, File}
import scala.io.StdIn
import scala.util.{Try, Success, Failure, Using}
import scala.annotation.tailrec


def test(input: String, output:String): Unit =
  println("Enter word length: ")
  val len = StdIn.readLine().toInt
  
  Using.Manager {use =>
    val source = use(scala.io.Source.fromFile(input))
    val outputFile = new File(output)
    outputFile.getParentFile().mkdirs()
    val writer = use(new PrintWriter(outputFile))
    val text = source.mkString
    val questions = """[A-Z][\w\s,']+?\?""".r.findAllIn(text).toList

    @tailrec def counter(sentences: List[String]): Unit = 
      sentences match
        case head :: next => {
          val words = s"\\b\\w{$len}\\b".r.findAllIn(head).distinct.toList
          writer.println(words)

          counter(next)
        }
        case Nil => ()
          
    counter(sentences = questions)
  } match
    case Failure(exception) => println(s"Exeption occured when working with file: ${exception.toString()}")
    case Success(_) => ()
      
  