import var1._
import var2._

@main def main(args: String* ): Unit = 
  val arglist = args.toList
  arglist match {
    case "-p"::tasks::tail if isListOfInts(tail) => run_tasks(tasks, (tail.map(_.toInt).toArray))
    case "--help"::tail => print_help()
    case tail if isListOfInts(tail) => run_tasks("1234", (tail.map(_.toInt).toArray))
    case tail => illegal_args(tail.mkString(" "))
  }

  def run_tasks(tasks: String, int: Array[Int]) =
    tasks.split("").foreach(_ match {
      case "1" =>{
        println("Variant 1")
        println("Question 4")
        var1.task1.main()
        println("-------------------------")
      }
      case "2" => {
        println("Variant 1")
        println("Question 5")
        var1.task2.main(int)
        println("-------------------------")
      }
      case "3" => {
        println("Variant 2")
        println("Question 4")
        var2.task1.main()
        println("-------------------------")
      }
      case "4" => {
        println("Variant 2")
        println("Question 5")
        var2.task2.main()
        println("-------------------------")
      }
      case arg => illegal_args("Task № "+arg) 
    })

  def isListOfInts(lst: List[String]) =
    lst.map(_.toIntOption).filter(_.isEmpty).isEmpty

  def print_help() =
    println("""
    lab1 [OPTIONS] [INTEGERS]
      [OPTIONS]
        --help      Prints help message
        -p TASKS    Allows partial execution of tasks. TASK is a sting of digits from 1 to 4.
      [INTEGERS]
        List of integers separated by space; used for Task 2 (Sum and mul)
    """)

  def illegal_args(arg: Any) =
    println("Illegal arguments found: "+arg)
    println("Usage:")
    print_help()