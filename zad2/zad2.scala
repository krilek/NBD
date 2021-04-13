def isWorkingDay(day: String): String = day match {
    case "Poniedziałek" | "Wtorek" | "Środa" | "Czwartek" | "Piątek" => "Praca"
    case "Sobota" | "Niedziela" => "Weekend"
    case _ => "Nie ma takiego dnia"
}

println("1. " + isWorkingDay("Poniedziałek") + " " + isWorkingDay("Niedziela") + " " + isWorkingDay("Test"))


class KontoBankowe(val stanKonta: Int = 0) {
  def wplata(ilosc: Int) : KontoBankowe = new KontoBankowe(stanKonta + ilosc);
  def wyplata(ilosc: Int) : KontoBankowe = new KontoBankowe(stanKonta - ilosc);
  override def toString: String = s"Stan konta: $stanKonta"
}

println(s"2. ${new KontoBankowe(100).wplata(10)} ${new KontoBankowe().wyplata(11)}")

case class Osoba(val imie: String, val nazwisko: String);

def greeting(p: Osoba): String = p match {
  case Osoba(imie, "Kowalski") => s"Jeden z 178103 osób, cześć $imie!"
  case Osoba("Adam", nazwisko) => s"Witaj Adamie twoje nazwisko to $nazwisko."
  case _ => "Witaj losowa osobo."
}

println(s"3. ${greeting(Osoba("Kuba", "Kowalski"))} ${greeting(Osoba("Adam", "Testowy"))} ${greeting(Osoba("Szymon", "Kowalewski"))}")

def executeThreeTimes(value: Int, funcToExecute: Int => Int) : Int = funcToExecute(funcToExecute(funcToExecute(value)))

println(s"4. 3*2*2*2 = ${executeThreeTimes(3, x => x * 2)}")


abstract class Person(val imie: String, 
                     val nazwisko: String) {
  def podatek: Double
}

trait Pracownik extends Person {
  var pensja: Int = 100
  override def podatek: Double = 0.2 * pensja
}

trait Student extends Person {
  override def podatek: Double = 0
}

trait Nauczyciel extends Pracownik {
  override def podatek: Double = 0.1 * pensja
}
val pracownik = new Person("Jan", "Kowalski") with Pracownik
pracownik.pensja = 1000
val student = new Person("Jan", "Kowalski") with Student
val studentPracownik = new Person("Jan", "Kowalski") with Student with Pracownik
studentPracownik.pensja = 1000
val pracownikStudent = new Person("Jan", "Kowalski") with Pracownik with Student
pracownikStudent.pensja = 1000
val nauczyciel = new Person("Jan", "Kowalski") with Nauczyciel
nauczyciel.pensja = 1000
println("5.")
println(s"Student trait podatek: ${student.podatek}")
println(s"Pracownik i Student trait podatek: ${pracownikStudent.podatek}")
println(s"Student i Pracownik trait podatek: ${studentPracownik.podatek}")
println(s"Nauczyciel trait podatek: ${nauczyciel.podatek}")
println(s"Pracownik trait podatek: ${pracownik.podatek}")
