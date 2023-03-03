package var2.task2
import scala.io.StdIn

def main() =
  println("Put down your ints separated by spaces:")
  val str = StdIn.readLine()
  val ints = str.split(" ").map(_.toInt)
  val sorted = bubbleSortByAbsDescending(ints)
  println(s"""Integers, sorted by their absolute values:
    |${sorted.mkString(" ")}""".stripMargin)

def bubbleSortByAbsDescending(arr: Array[Int]): Array[Int] = {
  var swapped = true
  var end = arr.length - 1
  while (swapped) {
    swapped = false
    for (i <- 0 until end) {
      if (math.abs(arr(i)) < math.abs(arr(i + 1))) {
        val temp = arr(i)
        arr(i) = arr(i + 1)
        arr(i + 1) = temp
        swapped = true
      }
    }
    end -= 1
  }
  arr
}
