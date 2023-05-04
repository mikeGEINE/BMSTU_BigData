package var2.task1

import java.io.{FileNotFoundException, IOException, PrintWriter, File}
import scala.io.StdIn
import scala.util.{Try, Success, Failure, Using}
import scala.annotation.tailrec


def test(input: String, output:String): Unit =
  Using.Manager {use =>
    val source = use(scala.io.Source.fromFile(input))
    val outputFile = new File(output)
    outputFile.getParentFile().mkdirs()
    val writer = use(new PrintWriter(outputFile))
    val sentences = source.mkString.split("[.!?]").toList

    @tailrec def counter(sentences: List[String]): Unit = 
      val vowels_regex = """[eyuioaEYUIOA]""".r
      sentences match
        case head :: next => {
          val vowels = vowels_regex.findAllIn(head).length
          val consonants = """\w""".r.findAllIn(head).length - vowels
          if vowels > consonants then
            writer.println("vowels")
          else
            writer.println("consonants")

          counter(next)
        }
        case Nil => ()
          
    counter(sentences = sentences)
  } match
    case Failure(exception) => println(s"Exeption occured when working with file: ${exception.toString()}")
    case Success(_) => ()
