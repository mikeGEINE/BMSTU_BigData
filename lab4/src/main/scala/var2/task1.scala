package var2.task1

trait Building:
  def getName(): String
  def getNumberOfRooms(): Int

abstract class PublicBuilding(name: String, numberOfRooms: Int) extends Building:
  def getName(): String = name
  def getNumberOfRooms(): Int = numberOfRooms

  def isOpen(): Boolean

class Theatre(name: String, numberOfRooms: Int) extends PublicBuilding(name, numberOfRooms):
  def isOpen(): Boolean = true

def test() =
  val theatre: Theatre = new Theatre("Palace Theatre", 3)

  println(s"""|Treating theatre as a theatre:
              |Theatre name: ${theatre.getName()}
              |Number of rooms: ${theatre.getNumberOfRooms()}
              |Is open: ${theatre.isOpen()}""".stripMargin)

  val building: Building = theatre

  println(s"""|Treating theatre as a building:
              |Building name: ${building.getName()}
              |Number of rooms: ${building.getNumberOfRooms()}""".stripMargin)
