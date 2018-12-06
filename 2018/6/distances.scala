import scala.io.Source

def manhattanDistance(a: (Int, Int), b: (Int, Int)): Int = {
  Math.abs(a._1 - b._1) + Math.abs(a._2 - b._2)
}

val coordinates = scala.collection.mutable.ListBuffer.empty[(Int, Int)]
for (line <- Source.fromFile("input").getLines) {
  coordinates += Tuple2(line.split(",")(0).trim.toInt,line.split(",")(1).trim.toInt)
}

println(manhattanDistance(coordinates(0),coordinates(1))

