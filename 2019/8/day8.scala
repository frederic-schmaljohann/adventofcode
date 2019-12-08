import scala.io.Source

val w = 25
val h = 6

val lines = (for (line <- Source.fromFile("input.txt").getLines()) yield line).toList

val layer = lines(0).grouped(w * h).minBy(x => x.count(_ == '0'))

layer.count(_ == '1') * layer.count(_ == '2')

val allLayers = lines(0).grouped(w * h).toList

val message = allLayers(0).zipWithIndex.map(it => {
  val theColorAtPos = allLayers.map(l => l(it._2)).foldRight(' ')((a, b) => if (a == '2') b else a)
  theColorAtPos
}).grouped(w)

message.foreach(line => {
    println(line.map(it => if (it == '0') "    " else "XXXX").mkString(" "))
})