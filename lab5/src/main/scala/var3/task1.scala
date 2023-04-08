package var3.task1

import scala.util.{Try, Success, Failure}
import java.io.{PrintWriter}
import scala.util.Using
import scala.annotation.tailrec

def readFromFile(filename: String) =
  Using(scala.io.Source.fromFile(filename))(_.getLines().toList.map(_.split(" ")).flatten)

def search(lst: List[String]) =
  @tailrec def recsearch(lst: List[String], acc: List[String]): List[String] =
    lst match
      case fst :: sec :: tail if fst.charAt(fst.length-1) == sec.charAt(0)  => recsearch(sec :: tail, fst :: acc)
      case head :: next => recsearch(next, acc)
      case Nil => acc
  recsearch(lst, Nil)
    
def test(input: String, output: String) = 
  readFromFile(filename = input) match
    case Failure(e) => println(s"Exeption occured when reading input file: ${e.toString()}")
    case Success(value) => 
      Using(new PrintWriter(output))(writer =>search(value).reverse.foreach(writer.println(_))) match
        case Failure(e) => println(s"Exeption occured when writing to output file: ${e.toString()}")
        case Success(u) => u
      
  