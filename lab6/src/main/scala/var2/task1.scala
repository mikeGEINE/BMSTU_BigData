package var2.task1

import scala.util.{Try, Success, Failure}
import scala.util.Using

def readFile(input: String) =
  Using(scala.io.Source.fromFile(input))(_.getLines().toList.map(name => (name, name.hashCode()))) match
    case Failure(e) => println(s"Exeption occured when reading input file: ${e.toString()}")
                        Nil
    case Success(value) => value

def test(input: String) =
  val lst = readFile(input)
  val c1 = lst.sortBy((name, code) => code).distinctBy((name, code) => name)

  println(s"""|List C1:
              |$c1""".stripMargin)
  