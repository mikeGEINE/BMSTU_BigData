package var4.task1

class Abiturient(
  private var _id: Int,
  private var _surname: String,
  private var _name: String,
  private var _patronymic: String,
  private var _address: String,
  private var _telephone: String,
  private var _marks: Map[String, Int] = Map.empty
):
  require(!(_marks.exists(mark => (mark._2 > 100) || (mark._2 < 0))), "Marks should be in range 0..100")

  def id = _id
  def id_=(id: Int) = _id = id

  def surname = _surname
  def surname_=(surname: String) = _surname = surname

  def name = _name
  def name_=(name: String) = _name = name

  def patronymic = _patronymic
  def patronymic_=(patronymic: String) = _patronymic = patronymic

  def address = _address
  def address_=(address: String) = _address = address

  def telephone = _telephone
  def telephone_=(telephone: String) = _telephone = telephone

  def marks = _marks
  def marks_=(marks: Map[String, Int]) = _marks = marks

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
    AbitList(_lst.filter(abt => abt.marks.values.exists(_<60)))

  def avgOver(threshold: Int, exams: List[Exam] = Nil) = 
    if exams.isEmpty then
      AbitList(_lst.filter(abt => (abt.marks.values.sum / abt.marks.values.size.toDouble) > threshold))
    else
      val subjects = exams.map(_.subject)
      val qualifiedApplicants = _lst.filter(abt => subjects.forall(subject => abt.marks.contains(subject)))
      AbitList(qualifiedApplicants.filter(
        abt => (abt.marks.filterKeys(subjects.contains).values.sum / subjects.length.toDouble) > threshold ))

  def top(n: Int = _lst.length, exams: List[Exam] = Nil) = 
    if exams.isEmpty then 
      AbitList(_lst.sortBy(abt => (abt.marks.values.sum / abt.marks.values.size.toDouble))(Ordering[Double].reverse).take(n))
    else
      val subjects = exams.map(_.subject)
      val qualifiedApplicants = _lst.filter(abt => subjects.forall(subject => abt.marks.contains(subject)))
      AbitList(qualifiedApplicants.sortBy(
        abt => (abt.marks.filterKeys(subjects.contains).values.sum / subjects.length.toDouble))
        (Ordering[Double].reverse).take(n))

  def passed(exams: List[Exam]) =
    AbitList(_lst.filter(abt => exams.forall(exam => abt.marks.contains(exam.subject))))


class Faculty(private val _name: String, private var _applicants: AbitList):
  def name = _name

  def applicants = _applicants

  def addApplicant(abt: Abiturient) = _applicants = new AbitList(_applicants.lst :+ abt)

  def removeApplicant(abt: Abiturient) = _applicants = new AbitList(_applicants.lst.filterNot(_ == abt))

class Exam(private val _subject: String, private val _maxScore: Int):
  def subject = _subject

  def maxScore = _maxScore

class Instructor(private val _name: String):
  def name = _name

  def grade(abt: Abiturient, exam: Exam, score: Int) = 
    require(score <= exam.maxScore, s"Score for the '${exam.subject}' exam cannot be over ${exam.maxScore}! (given $score)")
    abt.marks = abt.marks + ((exam.subject, score))

class Enrollment(private val _faculty: Faculty, private val _exams: List[Exam]):
  def faculty = _faculty

  def exams = _exams

  def enrollApplicants(threshold: Int, n: Int = faculty.applicants.lst.length): AbitList = 
    val qualifiedApplicants = _faculty.applicants.avgOver(threshold, _exams)
    qualifiedApplicants.top(n, _exams )

def test() =
  val faculty = new Faculty("Computer Science", new AbitList())

  val mathExam = new Exam("Math", 100)
  val csExam = new Exam("Computer Science", 100)

  val alice = new Abiturient(1, "Smith", "Alice", "A.", "123 Main St.", "555-1234")
  val bob = new Abiturient(2, "Jones", "Bob", "B.", "456 Elm St.", "555-5678")

  val instructor = new Instructor("John Doe")

  instructor.grade(alice, mathExam, 90)
  instructor.grade(alice, csExam, 80)
  instructor.grade(bob, mathExam, 70)
  instructor.grade(bob, csExam, 85)

  faculty.addApplicant(alice)
  faculty.addApplicant(bob)

  val enrollment = new Enrollment(faculty, List(mathExam, csExam))

  val enrolled = enrollment.enrollApplicants(75, 1)

  println(enrolled)


