package var1.task1

class Matrix(private val data: Array[Array[Int]]):
  require(data.length == data.head.length, "Matrix must be square!")

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
