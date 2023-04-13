package var2.task2

import scala.util.{Try, Success, Failure}
import scala.util.Using

def readFile(input: String) =
  Using(scala.io.Source.fromFile(input))(_.mkString.split(" ").toList.map(_.toInt).span(_ >= 0)) match
    case Failure(e) => println(s"Exeption occured when reading input file: ${e.toString()}");
                        (Nil, Nil)
    case Success(set1, set2) => (set1, set2.tail)

def zipSorted(a: List[Int], b: List[Int]) =
  def recursive(a: List[Int], b: List[Int], acc: List[Int]): List[Int] =
    (a, b) match
      case (ah :: at, bh :: bt) if ah < bh => recursive(at, b, ah :: acc)
      case (ah :: at, bh :: bt) if ah > bh => recursive(a, bt, bh :: acc)
      case (ah :: at, bh :: bt) => recursive(at, bt, ah :: bh :: acc)
      case (Nil, rst) => rst.reverse_:::(acc) 
      case (rst, Nil) => rst.reverse_:::(acc) 

  recursive(a, b, Nil)
    

def test(input: String) =
  val sets = readFile(input)
  val c1 = sets._1.sorted
  val c2 = sets._2.sorted
  val zipped = zipSorted(c1, c2)

  println(s"""|C1:
              |$c1
              |C2:
              |$c2
              |Zipped and sorted:
              |$zipped""".stripMargin)
