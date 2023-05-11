package var2.task2

def test() =
  val lst = List("ab", "cd", "ef", "abcd", "efgh")

  val map = lst.groupBy(_.charAt(0))

  println(s"""|List:
              |$lst
              |Map:
              |$map""".stripMargin)
