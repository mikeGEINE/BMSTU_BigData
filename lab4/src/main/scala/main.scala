import java.util.Date

@main def main(args: String* ): Unit = 
  val arglist = args.toList
  arglist match {
    case "-p"::tasks::tail => run_tasks(tasks)
    case "--help"::tail => print_help()
    case tail if tail.isEmpty => run_tasks("1234")
    case tail => illegal_args(tail.mkString(" "))
  }
  println(s"""|Developer: mikeGEINE
  |Task recieved on: Fri Mar 3 15:39:00 MSK 2023
  |Task completed (this run) on: ${new Date()}""".stripMargin)

def run_tasks(tasks: String) =
  tasks.split("").foreach { key =>
    key match {
    case "1" =>{
      println("Variant 1")
      println("Question 4")
      var1.task1.test()
    }
    case "2" => {
      println("Variant 1")
      println("Question 5")
      var1.task2.test()
    }
    case "3" => {
      println("Variant 2")
      println("Question 4")
      var2.task1.test()
    }
    case "4" => {
      println("Variant 2")
      println("Question 5")
      var2.task2.test()
    }
    case arg => illegal_args("Task № "+arg) 
    }
    println("-------------------------")
  }

def print_help() =
  println("""
  lab1 [OPTIONS]
    [OPTIONS]
      --help      Prints help message
      -p TASKS    Allows partial execution of tasks. TASK is a sting of digits from 1 to 4.
  """)

def illegal_args(arg: Any) =
  println("Illegal arguments found: "+arg)
  println("Usage:")
  print_help()