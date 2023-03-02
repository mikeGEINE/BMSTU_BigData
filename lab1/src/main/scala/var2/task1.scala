package var2.task1
import scala.io.StdIn

def main() =
  println("Put down your ints separated by spaces:")
  val str = StdIn.readLine()
  val ints = str.split(" ").map(_.toInt)
  val filtered = ints.filter( i => (divisible_by(i, 5) && divisible_by(i, 7)))
  println(s"""Integers, devisable both by 5 and by 7:
            |${filtered.mkString(" ")}""".stripMargin)

def divisible_by(n: Int, div: Int) =
  n % div == 0