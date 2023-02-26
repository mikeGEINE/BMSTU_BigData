import var1._
import var2._
import java.util.Date

@main def main(args: String* ): Unit = 
  val arglist = args.toList
  arglist match {
    case "-p"::tasks::tail if isListOfInts(tail) => run_tasks(tasks, (tail.map(_.toInt).toSeq))
    case "--help"::tail => print_help()
    case tail if isListOfInts(tail) => run_tasks("1234", (tail.map(_.toInt).toSeq))
    case tail => illegal_args(tail.mkString(" "))
  }
  println(s"""|Developer: mikeGEINE
  |Task recieved on: Fri Feb 17 15:39:00 MSK 2023
  |Task completed (this run) on: ${new Date()}""".stripMargin)

def run_tasks(tasks: String, int: Seq[Int]) =
  tasks.split("").foreach { key =>
    key match {
    case "1" =>{
      println("Variant 1")
      println("Question 4")
      var1.task1.main()
    }
    case "2" => {
      println("Variant 1")
      println("Question 5")
      var1.task2.main(int)
    }
    case "3" => {
      println("Variant 2")
      println("Question 4")
      var2.task1.main()
    }
    case "4" => {
      println("Variant 2")
      println("Question 5")
      var2.task2.main()
    }
    case arg => illegal_args("Task № "+arg) 
    }
    println("-------------------------")
  }

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