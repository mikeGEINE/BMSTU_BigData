package var1.task2

class Calendar {
  class DaysOff {
    private var holidays = List[String]()
    private var weekends = List[String]("Saturday", "Sunday")
    
    def addHoliday(date: String): Unit = {
      holidays = date :: holidays
    }
    
    def addWeekend(day: String): Unit = {
      weekends = day :: weekends
    }
    
    def isHoliday(date: String): Boolean = {
      holidays.contains(date)
    }
    
    def isWeekend(day: String): Boolean = {
      weekends.contains(day)
    }
  }
}

def test() =
  val calendar = new Calendar()
  val daysOff = new calendar.DaysOff()

  daysOff.addHoliday("2023-04-01")
  daysOff.addWeekend("Friday")

  val msg =
    s"""|Is 2023-04-01 a holiday? ${daysOff.isHoliday("2023-04-01")}
        |Is 2023-04-02 a holiday? ${daysOff.isHoliday("2023-04-02")}
        |Is friday a weekend? ${daysOff.isWeekend("Friday")}
        |Is monday a weekend? ${daysOff.isWeekend("Monday")}""".stripMargin
  println(msg)

