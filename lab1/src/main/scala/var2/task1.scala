package var2.task1
import bmstu.general._
import scala.io.StdIn

def main() =
  println("Put down your words separated by spaces:")
  val str = StdIn.readLine()
  val words = str.split(" ")
  val lengths = words.map(_.distinct.length()) // finds how many distinct letters in each word
  // words.apply(lengths.indexOf(lengths.min))
  print("Found word: ")
  lengths.min |> (lengths.indexOf(_)) |> words.apply |> (println(_)) // get minimum of distinct letters, 
              // get a corresponding position (first of), and retrieve the word by index from original array

