import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

def createOrbits(predecessorsOnly: Boolean = true) = {
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

    if (!predecessorsOnly) {
      val curr2 = if (orbits.contains(pair(0))) {
        orbits(pair(0))
      }
      else ListBuffer[String]()
      curr2 += pair(1)
      orbits.put(pair(0), curr2)
    }
  })
  orbits
}


def part1(): Unit = {
  val orbits = createOrbits(true)
  val all = orbits.keySet.toList

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
}

case class Node(label: String, predecessor: Node)
def bfs(g: mutable.Map[String, ListBuffer[String]], start: Node, dest: String): Node = {
  val orbitsSeen = mutable.Map[String, Boolean]()
  val q = mutable.Queue[Node]()
  orbitsSeen.put(start.label, true)
  q.enqueue(start)

  while (q.nonEmpty) {
    val v = q.dequeue()
    if (v.label == dest) {
      return v
    }
    g(v.label).foreach(w => {
      if (orbitsSeen.get(w).isEmpty) {
        orbitsSeen.put(w, true)
        val wN = Node(w, v)
        q.enqueue(wN)
      }
    })
  }
  null
}

def part2(): Unit = {
  var counter = 0
  val orbits = createOrbits(false)
  var p = bfs(orbits, Node("YOU", null), "SAN")
  while (p.label != orbits("YOU").head) {
    p = p.predecessor
    counter += 1
  }
  println(counter)
}

part1()
part2()
