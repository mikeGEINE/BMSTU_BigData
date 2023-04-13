package var1.task1

import scala.collection.immutable.HashMap

def polySum(a: HashMap[Int, Int], b: HashMap[Int, Int]): HashMap[Int, Int] =
  a.merged(b){ case ((k0, v0), (k1, v1)) => k0 -> (v0 + v1)}

def test() =
  val a = HashMap(0 -> 5, 1 -> 2, 2 -> 3, 4 -> 1)
  val b = HashMap(0 -> 1, 2 -> 2, 3 -> 7)

  println(s"""|Polynomial A: 
              |$a
              |Polynomial B: 
              |$b
              |A+B:
              |${polySum(a, b)}""".stripMargin)
