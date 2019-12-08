import scala.io.Source

val w = 25
val h = 6

val lines = (for (line <- Source.fromFile("input.txt").getLines()) yield line).toList

//part 1
val layer = lines(0).grouped(w * h).minBy(x => x.count(_ == '0'))

layer.count(_ == '1') * layer.count(_ == '2')

val allLayers = lines(0).grouped(w * h).toList

//part 2
allLayers(0).zipWithIndex.map(it => {
  allLayers.map(l => l(it._2)).foldRight(' ')((a, b) => if (a == '2') b else a)
}).grouped(w).foreach(line => {
    println(line.map(it => if (it == '0') "    " else "XXXX").mkString(" "))
})