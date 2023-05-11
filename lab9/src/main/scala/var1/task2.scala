package var1.task2

def test() =
  val lst = "this"::"is"::"a"::"string"::"list"::"of"::"string"::Nil

  println(s"""|Base list:
              |$lst
              |Filtered list:
              |${lst.distinct}""".stripMargin)
