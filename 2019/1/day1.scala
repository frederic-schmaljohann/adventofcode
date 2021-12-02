import scala.io.Source

val filename = "input.txt"
val masses = (for (line <- Source.fromFile(filename).getLines) yield (line.toInt)).toList

def calculateFuel(m: Int): Int = m  / 3 - 2

@scala.annotation.tailrec
def calculateFuelOverhead(m: Int, acc: Int): Int = {
  val fuel0 = calculateFuel(m)
  if (fuel0 > 0)
    calculateFuelOverhead(fuel0, acc + fuel0)
  else
    acc
}

println(masses.map(m => calculateFuel(m)).sum)

println(masses.map(m => calculateFuelOverhead(m, 0)).sum)