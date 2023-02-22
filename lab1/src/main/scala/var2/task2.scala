package var2.task2
import bmstu.general._
import scala.io.StdIn

def main() =
  println("Put down your words separated by spaces:")
  val str = StdIn.readLine()
  val words = str.split(" ")
  val latin_regex = """[^a-zA-Z]""".r.unanchored
  val latin = words.filter(! latin_regex.matches(_)) // if there is no other chars then [a-zA-Z], then a word is all latin
  println("Only latin words:")
  println(latin.mkString(" "))
  println("Words with eqal counts of vowels and consonants:")
  latin.filter(vowel_check(_)).mkString(" ") |> (println(_))


def vowel_check(word: String): Boolean =
  val vowels_regex = """[eyuioa]""".r
  val vowels = vowels_regex.findAllIn(word).length
  vowels == (word.length - vowels)