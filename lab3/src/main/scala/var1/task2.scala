package var1.task2

class Matrix(val data: Array[Array[Int]]) {
  // Constructor that takes in the dimensions and data array
  // and initializes the rows, cols and data instance variables
  def this(rows: Int, cols: Int) (fill_with: => Int) = this(data = Array.fill[Int](rows, cols)(fill_with))

  // Method to access a specific element in the matrix
  def apply(row: Int, col: Int): Double = data(row)(col)

  // Method to update a specific element in the matrix
  def update(row: Int, col: Int, value: Int): Unit = data(row)(col) = value

  // Method to print the matrix
  override def toString: String = "\n" + data.map(_.mkString(" ")).mkString("\n")
}

def swap(m:Matrix, k:Int): Matrix =
  val min = m.data.minBy(row => row(k))
  val minIndex = m.data.indexOf(min)
  val maxIndex = m.data.indexOf(m.data.maxBy(row => row(k)))
  m.data(minIndex) = m.data.maxBy(row => row(k))
  m.data(maxIndex) = min
  m

def square(arr: List[Matrix], i: Int): List[Matrix] =
  require(arr(i).data.length == arr(i).data(0).length, "To square a matrix it should be n*n dimensions!")
  val old = arr(i).data
  val size = old.length
  val newData = Array.fill(size, size)(0)
  for (i <- 0 until size; j <- 0 until size) {
    for (k <- 0 until size) {
      newData(i)(j) += old(i)(k) * old(k)(j)
    }
  }
  arr.updated(i, Matrix(newData))

def test() =
  val arr = List.fill(4)(Matrix(4,4){scala.util.Random.nextInt(5)})
  println(s"""|List of matrices: $arr
              |Swap by col 1: ${arr.map(swap(_,1))}
              |Square matrix 2: ${square(arr, 2)}""".stripMargin)

