package var2.task1

def test() =
  val lst = List("ab", "cd", "efg")

  val map = lst.groupMap(_.charAt(0))(_.charAt(1))

  println(s"""|List:
              |$lst
              |Map:
              |$map""".stripMargin)