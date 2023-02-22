package var1.task1
import bmstu.general._
import scala.io.StdIn

val password = "1234567890"

def main() =
  println("Enter password: ")
  val input = StdIn.readLine()
  (if password.equals(input) then "Password is correct!" else "Password is incorrect") |> (println(_))
  
