import scala.io.Source

val filename = "input.txt"
val masses = (for (line <- Source.fromFile(filename).getLines) yield (line.toInt)).toList

def calculateFuel(m: Int): Int = m  / 3 - 2

def calculateFuelOverhead(m: Int): Int = {
  val fuel0 = calculateFuel(m)
  if (fuel0 > 0)
      fuel0 + calculateFuelOverhead(fuel0)
  else
    0
}

println(masses.map(m => calculateFuel(m)).sum)

println(masses.map(m => calculateFuelOverhead(m)).sum)