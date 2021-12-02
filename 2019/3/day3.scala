import scala.io.Source

val lines = (for (line <- Source.fromFile("input.txt").getLines()) yield line).toList

val directionsW1: Array[String] = lines(0).split(',')
val directionsW2: Array[String] = lines(1).split(',')

def computePositions(directions: Array[String]): List[(Int, Int, Int)] = {
  var positions = List[(Int, Int, Int)]()
  var currPos = (0, 0, 0)
  var steps = 1
  directions.foreach(dir => {
      var i = 0
      while (i < dir.substring(1).toInt) {
        val incVert = if (dir.startsWith("R")) 1 else if (dir.startsWith("L")) -1  else 0
        val incHor = if (dir.startsWith("U")) 1 else if (dir.startsWith("D")) -1  else 0
        currPos = (currPos._1 + incHor, currPos._2 + incVert, steps)
        i = i + 1
        positions = currPos :: positions
        steps = steps + 1
      }
  }
  )
  positions
}

val positionsW1 = computePositions(directionsW1)
val positionsW2 = computePositions(directionsW2)

val matches = positionsW1.map(it => (it._1, it._2)).intersect(positionsW2.map(it => (it._1, it._2)))

//part 1
val closestMatch = matches.minBy(it => Math.abs(it._1) + Math.abs(it._2))
println(Math.abs(closestMatch._1) + Math.abs(closestMatch._2))

//part 2
val firstMatch = matches.map(d => {
  val x = positionsW1.find(it => it._1 == d._1 && it._2 == d._2)
  val y = positionsW2.find(it => it._1 == d._1 && it._2 == d._2)
  (x.get._3, y.get._3)
}).minBy(it => it._1 + it._2)

println(firstMatch._1 + firstMatch._2)
