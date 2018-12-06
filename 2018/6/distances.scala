import scala.io.Source
import scala.collection.mutable.ListBuffer

case class Coord(x: Int, y: Int, id: String)

def manhattanDistance(a: (Int, Int), b: (Int, Int)): Int = {
  Math.abs(a._1 - b._1) + Math.abs(a._2 - b._2)
}

val coordinates = ListBuffer.empty[Coord]
var i = 0
for (line <- Source.fromFile("input").getLines) {
  coordinates += Coord(x = line.split(",")(0).trim.toInt, y = line.split(",")(1).trim.toInt, i.toString)
  i = i + 1
}

// part 1
def run(dX: Int, dY: Int): Seq[(String, Int)] = {
  (-dX to +dX).flatMap(x => {
    (-dY to +dY).flatMap(y => {
      // checkDistance to all coordinates
      val mins = coordinates.map(c => (manhattanDistance((x, y), (c.x, c.y)), c.id)).sorted.take(2)
      if (mins.head._1 == mins(1)._1) None else List(mins.head)
    })
  }).toList.groupBy(it => it._2).mapValues(_.size).toSeq.sorted
}
val run1 = run(600, 600)
val run2 = run(700, 700)
run1.toList.zip(run2.toList).filter(it => it._1._2 == it._2._2).maxBy(_._1._2)._1._2

// part 2
(-2001 to +2001).flatMap(x => {
  (-2001 to +2001).map(y => {
    // checkDistance to all coordinates
    coordinates.map(c => {
      manhattanDistance((x, y), (c.x, c.y))
    }).sum
  })
}).count(_ < 10000)
