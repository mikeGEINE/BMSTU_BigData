package var2.task1

import scala.util.{Try, Success, Failure}
import java.io.{FileNotFoundException, IOException, PrintWriter}

class InvalidMarkException(msg: String) extends Exception(msg)

class Abiturient(
  private var _id: Int,
  private var _surname: String,
  private var _name: String,
  private var _patronymic: String,
  private var _address: String,
  private var _telephone: String,
  private var _marks: List[Int]
):
  require(!(_marks.exists(mark => (mark>100) || (mark<0))), "Marks should be in range 0..100")

  def id = _id
  def id_=(id:Int) = _id = id

  def surname = _surname
  def surname_=(surname:String) = _surname = surname

  def name = _name
  def name_=(name:String) = _name = name

  def patronymic = _patronymic
  def patronymic_=(patronymic:String) = _patronymic = patronymic

  def address = _address
  def address_=(address:String) = _address = address

  def telephone = _telephone
  def telephone_=(telephone:String) = _telephone = telephone

  def marks = _marks
  def marks_=(marks:List[Int]) =
    if(marks.exists(mark => (mark>100) || (mark<0)))
      throw new InvalidMarkException("Marks should be in range 0..100")
    _marks = marks

  override def toString: String = s"""|Abiturient №$id: $surname $name $patronymic
                                      |Address: $address
                                      |Telephone: $telephone
                                      |Marks: ${marks.mkString(", ")}""".stripMargin


class AbitList(private val _lst: List[Abiturient]):
  def this(abts: Abiturient* ) = 
    this(_lst = List.from(abts))

  override def toString: String = lst.mkString("\n------\n")

  def lst = _lst

  def apply(i:Int) = lst(i)

  def unsatisfactory = 
    AbitList(_lst.filter(abt => abt.marks.exists(_<60)))

  def avgOver(threshold: Int) = 
    AbitList(_lst.filter(abt => 
      Try(abt.marks.sum / abt.marks.length.toDouble) match
        case Success(avg) => avg > threshold
        case Failure(exception) => throw exception))

  def top(n: Int = _lst.length) = 
    AbitList(_lst.sortBy(abt => 
      Try(abt.marks.sum / abt.marks.length.toDouble) match
        case Success(avg) => avg
        case Failure(exception) => throw exception)(Ordering[Double].reverse).take(n))

object AbitList: 
  def readFromFile(filename: String): Try[AbitList] = 
    Try {
      val source = scala.io.Source.fromFile(filename)
      val lines = source.getLines().toList
      source.close()
      val abts = lines.map(line =>
        val fields = line.split(";")
        val id = fields(0).toInt
        val surname = fields(1)
        val name = fields(2)
        val patronymic = fields(3)
        val address = fields(4)
        val telephone = fields(5)
        val marks = fields.drop(6).map(_.toInt).toList
        Abiturient(id, surname, name, patronymic, address, telephone, marks))
      new AbitList(abts)
    }.recoverWith {
      case e: FileNotFoundException => Failure(new IOException("File not found", e))
      case e: IOException => Failure(new IOException("Error reading file", e))
      case e: Exception => Failure(e)
    }

  def writeToFile(filename: String, abts: AbitList): Try[Unit] = 
    Try {
      val writer = new PrintWriter(filename)
      for (abt <- abts.lst) {
        val line = List(
          abt.id.toString,
          abt.surname,
          abt.name,
          abt.patronymic,
          abt.address,
          abt.telephone
        ).mkString(";") + ";" + abt.marks.mkString(";")
        writer.println(line)
      }
      writer.close()
    }.recoverWith {
      case e: IOException => Failure(new IOException("Error writing file", e))
      case e: Exception => Failure(e)
    }


def randAbit(id: Int = scala.util.Random.nextInt(1000)) =
  Abiturient(
    _id = id,
    _surname = scala.util.Random.alphanumeric.take(scala.util.Random.nextInt(15)).mkString,
    _name = scala.util.Random.alphanumeric.take(scala.util.Random.nextInt(15)).mkString,
    _patronymic = scala.util.Random.alphanumeric.take(scala.util.Random.nextInt(15)).mkString,
    _telephone = List.fill(10){scala.util.Random.between(0,10)}.mkString,
    _address = scala.util.Random.alphanumeric.take(scala.util.Random.nextInt(20)).mkString,
    _marks = List.fill(3){scala.util.Random.between(50,101)}
  )

def randAbitList(n: Int = 5) =
  AbitList(List.tabulate(n)(i => randAbit(i)))

def test() = 
  val lst = randAbitList(7)
  println(s"""|Abiturients: 
              |$lst
              |~~~~~~~
              |Unsatisfactory marks:
              |${lst.unsatisfactory}
              |~~~~~~~
              |Average over 80:
              |${lst.avgOver(80)}
              |~~~~~~~
              |Top 2:
              |${lst.top(2)}""".stripMargin)
