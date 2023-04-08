package var3.task2

import scala.util.{Try, Success, Failure}
import java.io.{PrintWriter}
import scala.util.Using
import scala.annotation.tailrec

def readFromFile(filename: String) =
  Using(scala.io.Source.fromFile(filename))(_.getLines().toList.map(_.split("").toList.map(_.toInt)))

def search(lst: List[Int]) =
  @tailrec def recsearch(lst: List[Int], acc: Int, max: Int): Int =
    lst match
      case a :: b :: tail if a == b || a == b-1 || a == b+1 =>
        if acc == 0 then recsearch(b :: tail, 2, if 2 > max then 2 else max)
        else recsearch(b :: tail, acc+1, if acc+1 > max then acc+1 else max)
      case head :: next => recsearch(next, 0, max)
      case Nil => max
  recsearch(lst, 0, 0)

def test(input: String, output: String) = 
  readFromFile(filename = input) match
    case Failure(e) => println(s"Exeption occured when reading input file: ${e.toString()}")
    case Success(value) => 
      val lengths = value.map(search(_))
      Using(new PrintWriter(output))(writer => lengths.foreach(writer.println(_))) match
        case Failure(e) => println(s"Exeption occured when writing to output file: ${e.toString()}")
        case Success(u) => u
      


    