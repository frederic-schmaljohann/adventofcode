import scala.collection.mutable
import scala.io.Source
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer


val orbits = mutable.Map[String, ListBuffer[String]]()

val lines = (for (line <- Source.fromFile("input.txt").getLines()) yield line).toList

lines.foreach(l => {
  val pair = l.split("\\)")

  val curr = if (orbits.contains(pair(1))) {
    orbits(pair(1))
  }
  else ListBuffer[String]()
  curr += pair(0)

  orbits.put(pair(1), curr)

  val curr2 = if (orbits.contains(pair(0))) {
    orbits(pair(0))
  }
  else ListBuffer[String]()
  curr2 += pair(1)

  orbits.put(pair(0), curr2)
})

val all = orbits.keySet.toList

//part 1
@tailrec
def process(start: String, acc: Int): Int = {
  if (start == "COM") {
    acc
  } else {
    println(s"$start orbits around ${orbits(start)}")
    process(orbits(start).head, acc + 1)
  }
}

println(all.map(it => {
  process(it, 0)
}).sum)

//part 2
val distances = mutable.Map[String, Int]()
val predecessors = mutable.Map[String, Option[String]]()

def updateDistance(u: String, v: String): Unit = {
  if (distances(u) + 1 < distances(v)) {
    distances(v) = distances(u) + 1
    predecessors(v) = Some(u)
  }
}

all.foreach(s => {
  distances.put(s, Int.MaxValue)
  predecessors.put(s, None)
})

distances.put("YOU", 0)
val q = all.to[ListBuffer]

while (q.nonEmpty) {
  val u = q.minBy(it => distances(it))
  q.remove(q.indexOf(u))

  val neighbours = orbits(u)
  neighbours.foreach(neighbour => {
    if (orbits.contains(u) && q.contains(neighbour)) {
      updateDistance(u, neighbour)
    }
  })
}

var x = orbits("SAN").head
var cnt = 0
while (x != orbits("YOU").head) {
  x = predecessors(x).get
  println(x)
  cnt += 1
}

println(cnt)
