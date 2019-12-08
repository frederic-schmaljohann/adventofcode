import scala.io.Source

val w = 25
val h = 6

val lines = (for (line <- Source.fromFile("input.txt").getLines()) yield line).toList

val layer = lines(0).grouped(w * h).minBy(x => x.count(_ == '0'))

layer.count(_ == '1') * layer.count(_ == '2')

val allLayers = lines(0).grouped(w * h).toList

val message = allLayers(0).zipWithIndex.map(it => {
  var theColorAtPos = ' '
  var numLayers = allLayers.length - 1
  while (numLayers >= 0) {
    val currLayer = allLayers(numLayers)
    val currColor = currLayer(it._2)
    if (currColor == '0') {
      theColorAtPos = '0'
    }
    if (currColor == '1') {
      theColorAtPos = '1'
    }
    numLayers = numLayers - 1
  }
  theColorAtPos
}).grouped(w)

message.foreach(line => {
    println(line.map(it => if (it == '0') "    " else "XXXX").mkString(" "))
})