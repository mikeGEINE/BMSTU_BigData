package var2.task2
import bmstu.general._
import scala.io.StdIn
import scala.util.Random

def main() =
  println("Enter size of a matrix: ")
  val n = StdIn.readInt()
  println("The Matrix:")
  val matrix = createMatrix(n)
  printMatrix(matrix)
  println(s"Transposed matrix:")
  printMatrix(matrix.transpose)

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


