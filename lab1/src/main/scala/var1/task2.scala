package var1.task2
import scala.io.StdIn
import bmstu.general

def main(arr: Seq[Int] ): Unit = 
  println(s"CML arguments sum: ${arr.fold(0)(_ + _)}") 
  println(s"CML arguments mul: ${arr.fold(1)(_ * _)}") 
