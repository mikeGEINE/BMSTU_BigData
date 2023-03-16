package var3.task2

class Door(val name: String, var locked: Boolean = false):
  def lock() = locked = true

  def unlock() = locked = false

  def toggle() = locked = !locked

  override def toString(): String = s"Door $name, locked: $locked"

  override def hashCode(): Int = name.hashCode()

  override def equals(x: Any): Boolean = x match
    case that: Door => name.equals(that.name)
    case _ => false

class Window(val name: String):
  override def toString(): String = s"Window $name"

  override def hashCode(): Int = name.hashCode()

  override def equals(x: Any): Boolean = x match
    case that: Window => name.equals(that.name)
    case _ => false
  
class House(val name: String, val doors: List[Door], val windows: List[Window]):
  require(doors.length > 0, "There should be at least 1 door in a house!")

  def lock() = doors.foreach(_.lock())

  def unlock() = doors.foreach(_.unlock())

  def toggle() = doors.foreach(_.toggle())

  def countDoors = doors.length

  def countWindows = windows.length

  override def toString(): String =
    s"""|House $name:
        |Doors:
        |${doors.mkString("\n")}
        |Windows:
        |${windows.mkString("\n")}
        |""".stripMargin

  override def equals(x: Any): Boolean = x match
    case that: House => name.equals(that.name) && doors.equals(that.doors) && windows.equals(that.windows)
    case _ => false

  override def hashCode(): Int = name.hashCode() + doors.map(_.hashCode()).sum + windows.map(_.hashCode()).sum

def test() =
  val house1 = House("adin", List(Door("door1"), Door("door2")), List(Window("Shindows")))
  val house2 = House("Dva", List(Door("door2"), Door("backdoor"), Door("door1")), List(Window("window1")))
  val eq_house1 = House("adin", List(Door("door1"), Door("door2")), List(Window("Shindows")))

  println(s"""|House1:
              |$house1
              |House2:
              |$house2
              |Lock all doors in house1
              |${house1.lock(); house1}
              |Doors in house2: ${house2.countDoors}
              |Windows in house1: ${house1.countWindows}
              |house1==house2: ${house1.equals(house2)}
              |house1==eq_house1: ${house1.equals(eq_house1)}
              |house1 hash: ${house1.hashCode()}""".stripMargin)
