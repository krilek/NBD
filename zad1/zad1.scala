import scala.annotation.tailrec
val weekDays = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
def joinWeekDaysFor(days: List[String]) : String = {
      var out: String = ""
      for( day <- days){ 
            if (out.length() > 0)
                out += ", "
            out += day
      } 
      return out
}
println("For: "+joinWeekDaysFor(weekDays))

def joinWeekDaysThatStartWithPFor(days: List[String]) : String = {
      return joinWeekDaysFor(days.filter(d => d.startsWith("P") || d.startsWith("p")))
}
println("Starts with P: "+joinWeekDaysThatStartWithPFor(weekDays))

def joinWeekDaysWhile(days: List[String]) : String = {
      var out: String = ""
      var i: Int = 0
      
      while(i < days.size){
            if (out.length() > 0)
                out += ", "
            out += days(i)
            i += 1
      }
      return out
}

println("While: "+joinWeekDaysWhile(weekDays))


def joinWeekDaysRec(days: List[String]): String = days match {
    case d :: Nil => d
    case d :: tail => d + ", " + joinWeekDaysRec(tail)
    case Nil => ""
}

println("Recursive: "+joinWeekDaysRec(weekDays))

def joinWeekDaysRecReverse(days: List[String]): String = days match {
    case d :: Nil => d
    case d :: tail => joinWeekDaysRecReverse(tail) + ", " + d
    case Nil => ""
}

println("Reverse: "+joinWeekDaysRecReverse(weekDays))

@annotation.tailrec
def joinWeekDaysTailRec(days: List[String], out: String = ""): String = days match {
    case d :: List() => out + d 
    case d :: tail => joinWeekDaysTailRec(tail, out + d + ", ")
    case List() => out
}

println("Tailrec: "+joinWeekDaysTailRec(weekDays))

def foldLWeekDays(days: List[String]) = days.tail.foldLeft(days.head)((prev, curr) => prev+ ", " + curr)

println("Fold Left: "+foldLWeekDays(weekDays))

def foldRWeekDays(days: List[String]) = days.dropRight(1).foldRight(days.last)((a, b) => a + ", " + b)

println("Fold Right: "+foldRWeekDays(weekDays))

// Didn't know if filter is allowed
// def foldLWeekDaysStartP(days: List[String]) = 
//   foldLWeekDays(days.filter(d => d.startsWith("P") || d.startsWith("p")))
val filterAndConcat: (String, String) => String = 
  (prev, curr) => if (curr.startsWith("P") || curr.startsWith("p"))
                      if (prev == "") prev + curr else prev+ ", " + curr
                  else prev + ""
def foldLWeekDaysStartP(days: List[String]) = 
  days.foldLeft("")(filterAndConcat)

println("Fold Left and starts with P: "+foldLWeekDaysStartP(weekDays))

val products = Map("Chleb" -> 10.0, "Jogurt" -> 15.0, "Ser" -> 30.5)

println("5.Products price reduced 10% " + products.map((pr) => pr._1 -> pr._2 * 0.9))

def filterOutZeroes(ints: List[Int]): List[Int] = ints match {
  case d :: tail if d == 0 => filterOutZeroes(tail)
  case d :: tail => d :: filterOutZeroes(tail)
  case List() => ints
}

println("8.Without zeroes " + filterOutZeroes(List(1,2,3,0,5,6,0,7)))

def increment(ints: List[Int]): List[Int] = ints match {
  case d :: tail => d + 1 :: increment(tail)
  case List() => ints
}
println("9.Map incremented " + increment(List(1,2,3,0,5,6,0,7)))


def filterAndAbs(vals: List[Double]): List[Double] = vals match {
  case d :: tail if d >= -5 && d <= 12 => d.abs :: filterAndAbs(tail)
  case List() => vals
  case _ :: tail => filterAndAbs(tail)
}
println("10.Filter and abs " + filterAndAbs(List(-7,-5,-4.2,-3,2,7.7,11.9,12,33.3)))
