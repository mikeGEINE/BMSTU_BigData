package var2.task2
import bmstu.general._
import scala.io.StdIn

def main() =
  println("Put down your words separated by spaces:")
  val str = StdIn.readLine()
  val words = str.split(" ")
  val latin_regex = """[^a-zA-Z]""".r.unanchored
  val latin = words.filter(! latin_regex.matches(_)) // if there is no other chars then [a-zA-Z], then a word is all latin
  println(s"Only latin words: ${latin.length}")
  println(latin.mkString(" "))
  val latin_eq = latin.filter(vowel_check(_))
  println(s"Words with equal counts of vowels and consonants: ${latin_eq.length}")
  println(latin_eq.mkString(" "))


def vowel_check(word: String): Boolean =
  val vowels_regex = """[eyuioaEYUIOA]""".r
  val vowels = vowels_regex.findAllIn(word).length
  vowels == (word.length - vowels)