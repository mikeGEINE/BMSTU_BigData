package var2.task1
import bmstu.general._
import scala.io.StdIn
import scala.util.Random

def main() =
  println("Enter size of a matrix: ")
  val n = StdIn.readInt()
  println("The Matrix:")
  val matrix = createMatrix(n)
  printMatrix(matrix)
  println(s"Sum of elements: ${matrix.map(sumBetweenPositives(_)).sum}")

def createMatrix(n: Int): Array[Array[Int]] = 
  val random = new Random()
  val matrix = Array.ofDim[Int](n, n)
  for (i <- 0 until n; j <- 0 until n) {
    matrix(i)(j) = random.nextInt(2 * n + 1) - n
  }
  matrix

def printMatrix(matrix: Array[Array[Int]]):Unit =
  for (row <- matrix) {
  println(row.mkString(", "))
  }

def sumBetweenPositives(row: Array[Int]): Int =
  val firstPos = row.indexWhere(_ > 0, from = 0)
  if firstPos >= 0 then
    val secondPos = row.indexWhere(_ > 0, from = firstPos + 1)
    if secondPos > 0 then
      row.slice(firstPos + 1, secondPos).sum
    else
      row.slice(firstPos + 1, row.length).sum
  else
    row.sum
  
