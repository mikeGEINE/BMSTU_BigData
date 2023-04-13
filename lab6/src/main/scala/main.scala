import java.util.Date
import scala.util.{Try, Success, Failure}
import scala.util.Using
import scala.annotation.tailrec

@main def main(args: String* ): Unit = 
  val arglist = args.toList
  val argmap = parseArgs(arglist)
  argmap.get("help") match
    case None => 
      val tasks = argmap.get("tasks") match
        case None => "12"
        case Some(value) => value

      argmap.get("io") match
        case None => 
          (argmap.get("input"), argmap.get("output")) match
            case (Some(input), Some(output)) => run_tasks(tasks, input, output)
            case (Some(input), None) => run_tasks(tasks, input, "output")
            case (None, Some(output)) => run_tasks(tasks, "input", output)
            case (None,None) => run_tasks(tasks, "input", "output")
        case Some(value) =>
          Using(scala.io.Source.fromFile(value))(iter => iter.getLines().take(2).toList) match
            case Success(files) => run_tasks(tasks, files(0), files(1))
            case Failure(e) => println(s"Exeption occured when reading io file: ${e.toString()}")
    case Some(_) =>
      print_help()
  

  println(s"""|Developer: mikeGEINE
  |Task recieved on: Mon Mar 10 19:34:00 MSK 2023
  |Task completed (this run) on: ${new Date()}""".stripMargin)

def parseArgs(list: List[String]): Map[String, String] =
  @tailrec def recparse(lst:List[String], acc: Map[String, String]): Map[String, String] =
    lst match
      case "-p" :: tasks :: tail => recparse(tail, acc = acc + (("tasks", tasks)))
      case "-o" :: output :: tail => recparse(tail, acc = acc + (("output", output)))
      case "-i" :: input :: tail => recparse(tail, acc = acc + (("input", input)))
      case "-io" :: file :: tail => recparse(tail, acc = acc + (("io", file)))
      case "--help" :: tail => recparse(tail, acc = acc + (("help", "")))
      case head :: next => recparse(next, acc)
      case Nil => acc

  recparse(list, Map.empty)

def run_tasks(tasks: String, input: String, output: String) =
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
      var2.task1.test(input)
    }
    case "4" => {
      println("Variant 2")
      println("Question 5")
      var2.task2.test(input)
    }
    case arg => illegal_args("Task № "+arg) 
    }
    println("-------------------------")
  }

def print_help() =
  println("""
  |lab5 [OPTIONS]
  | [OPTIONS]
  |   --help      Prints help message
  |   -p TASKS    Allows partial execution of tasks. TASK is a sting of digits from 1 to 8.
  |   -io PATH    Path to a file with paths to input and output files (overrides -i and -o).
  |   -i PATH     Path to an input file (DEFAULT=input). 
  |   -o PATH     Path to an output file (DEFAULT=output)
  """.stripMargin)

def illegal_args(arg: Any) =
  println("Illegal arguments found: "+arg)
  println("Usage:")
  print_help()