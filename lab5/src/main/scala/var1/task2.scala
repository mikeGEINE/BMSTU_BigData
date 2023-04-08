package var1.task2

class Matrix (val data: Vector[Vector[Int]]) {

  def this(rows: Int, cols: Int)(fill_with: => Int) = 
    this(Vector.tabulate(rows, cols)((_, _) => fill_with))

  def apply(row: Int, col: Int): Int = data(row)(col)

  def updated(row: Int, col: Int, value: Int): Matrix = 
    new Matrix(data.updated(row, data(row).updated(col, value)))

  override def toString: String = "\n" + data.map(_.mkString(" ")).mkString("\n")

  def swap(k: Int): Matrix = {
    val minIndex = data.indices.minBy(i => data(i)(k))
    val maxIndex = data.indices.maxBy(i => data(i)(k))
    val swappedRows = data.updated(minIndex, data(maxIndex)).updated(maxIndex, data(minIndex))
    new Matrix(swappedRows)
  }
}

def square(arr: List[Matrix], i: Int): List[Matrix] = {
  require(arr(i).data.forall(_.length == arr(i).data.length), "To square a matrix it should be n*n dimensions!")
  val oldData = arr(i).data
  val size = oldData.length
  val newData = Vector.tabulate(size, size) { (i, j) =>
    (0 until size).map(k => oldData(i)(k) * oldData(k)(j)).sum
  }
  arr.updated(i, new Matrix(newData)) 
}

def test() =
  val arr = List.fill(4)(Matrix(4,4){scala.util.Random.nextInt(5)})
  println(s"""|List of matrices: $arr
              |Swap by col 1: ${arr.map(_.swap(1))}
              |Square matrix 2: ${square(arr, 2)}""".stripMargin)

