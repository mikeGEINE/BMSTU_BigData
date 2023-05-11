package var1.task1

def test() =
  val lst = List.range(1, 11)

  val (even, odd) = lst.partition(_ % 2 == 0)

  println(s"""|Original list:
              |$lst
              |Even:
              |$even
              |Odd:
              |$odd""".stripMargin)