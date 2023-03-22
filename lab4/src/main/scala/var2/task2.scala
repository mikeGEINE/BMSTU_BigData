package var2.task2

trait Mobile:
  def call(): Unit
  def brand: String

abstract class SiemensMobile(owner: String) extends Mobile:
  def call(): Unit = println(s"$owner is making a call with Siemens Mobile")

  def brand: String = "Siemens"

  def identify(): String

class Model(modelName: String, owner: String) extends SiemensMobile(owner):
  def identify(): String = s"This is Siemens $modelName"

def test() =
  val m55 = new Model("M55", "Shrek")

  println(s"""|Testing new mobile phone!
              |This is a mobile phone by ${m55.brand}
              |${m55.identify()}""".stripMargin)

  m55.call()

