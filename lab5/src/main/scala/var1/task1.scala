package var1.task1

import java.io.{FileNotFoundException, IOException, PrintWriter}
import scala.util.{Try, Success, Failure}

class Matrix(private val data: Array[Array[Int]]):
  require(data.forall(_.length == data.length), "Matrix must be square!")

  def this(n: Int) (fill_with: => Int) = 
    this(data = Array.fill(n,n)(fill_with))
  
  def size: Int = data.length

  def apply(i: Int, j: Int): Int= data(i)(j)

  def update(i: Int, j: Int, value: Int): Unit =
    data(i)(j) = value

  def sum (other: Matrix): Matrix = 
    require(this.size == other.size, "Dimensions of the matrixes should be equal!")
    
    val newData = Array.fill(size, size)(0)
    for (i <- 0 until size; j <- 0 until size) {
      newData(i)(j) = data(i)(j) + other(i, j)
    }
    new Matrix(newData)

  def +(other: Matrix): Matrix = sum(other)

  def sub (other: Matrix): Matrix = 
    require(this.size == other.size, "Dimensions of the matrixes should be equal!")
    
    val newData = Array.fill(size, size)(0)
    for (i <- 0 until size; j <- 0 until size) {
      newData(i)(j) = data(i)(j) - other(i, j)
    }
    new Matrix(newData)

  def -(other: Matrix): Matrix = sub(other)

  def mul(other: Matrix) =
    require(this.size == other.size, "Dimensions of the matrixes should be equal!")

    val newData = Array.fill(size, size)(0)
    for (i <- 0 until size; j <- 0 until size) {
      for (k <- 0 until size) {
        newData(i)(j) += data(i)(k) * other(k, j)
      }
    }
    new Matrix(newData)

  def *(other: Matrix): Matrix = mul(other)

  override def toString: String = "\n" + data.map(_.mkString(" ")).mkString("\n")

  def norm1:Int =
    data.map(_.sum).max

  def norm2:Int =
    (0 until size).map(j => data.map( row => row(j)).sum).max

  def saveToFileExn(filename: String): Unit = 
    Try(new PrintWriter(filename)) match 
      case Success(writer) =>
        try 
          data.foreach(row => writer.println(row.mkString(",")))
        finally
          writer.close()
      case Failure(ex) =>
        throw new IOException(s"Failed to save matrix to file '$filename': ${ex.getMessage}")

  def saveToFile(filename: String): Try[Unit] = 
    Try(new PrintWriter(filename)) match 
      case Success(writer) =>
        val res = Try(data.foreach(row => writer.println(row.mkString(","))))
        writer.close()
        res
      case Failure(ex) =>
        Failure(IOException(s"Failed to save matrix to file '$filename': ${ex.getMessage}"))

object Matrix:
  def loadFromFileExn(filename: String): Matrix = 
    Try(io.Source.fromFile(filename).getLines().map(line => line.split(",").map(_.toInt).toArray).toArray) match
      case Success(rows) =>
        if (rows.forall(_.length == rows.length))
          new Matrix(rows)
        else
          throw new IllegalArgumentException(s"Invalid matrix size in file '$filename'")
      case Failure(ex: FileNotFoundException) =>
        throw ex
      case Failure(ex) =>
        throw new IOException(s"Failed to load matrix from file '$filename': ${ex.getMessage}")

  def loadFromFile(filename: String): Try[Matrix] = 
    Try(io.Source.fromFile(filename).getLines().map(line => line.split(",").map(_.toInt).toArray).toArray) match
      case Success(rows) =>
        if (rows.forall(_.length == rows.length))
          Success(new Matrix(rows))
        else
          Failure(new IllegalArgumentException(s"Invalid matrix size in file '$filename'"))
      case Failure(ex: FileNotFoundException) =>
        Failure(ex)
      case Failure(ex) =>
        Failure(new IOException(s"Failed to load matrix from file '$filename': ${ex.getMessage}"))

def test(): Unit =
  val a = Matrix(4){scala.util.Random.nextInt(5)}
  val b = Matrix(4){scala.util.Random.nextInt(5)}

  println(s"""|Matrix op test.
              |Matrix A: $a
              |Matrix B: $b
              |A+B= ${a+b}
              |A-B= ${a-b}
              |A*B= ${a*b}""".stripMargin)

  val arr = List.fill[Matrix](4){Matrix(4){scala.util.Random.nextInt(5)}}

  println(s"""|Matrix array & norms test.
              |Array:
              |${arr.toString()}
              |Norm1: ${arr.map(_.norm1).toString()}
              |Norm2: ${arr.map(_.norm2).toString()}
              |Max norm 1 in matrix: ${arr.indexOf(arr.maxBy(_.norm1))}
              |Max norm 2 in matrix: ${arr.indexOf(arr.maxBy(_.norm2))}
              |Test complete!""".stripMargin)
