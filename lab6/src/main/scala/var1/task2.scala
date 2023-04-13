package var1.task2

def multiplyPolynomials(a: List[Int], b: List[Int]): List[Int] = 
  val m = a.length
  val n = b.length
  val result = Array.fill(m + n - 1)(0)

  for (i <- 0 until m) 
    for (j <- 0 until n) 
      result(i + j) += a(i) * b(j)

  result.toList

def test() =
  val a = List(5, 2, 3)
  val b = List(2, 0, -1, 3)

  println(s"""|Polynomial A:
              |$a
              |Polynomial B:
              |$b
              |A*B:
              |${multiplyPolynomials(a, b)}""".stripMargin)
