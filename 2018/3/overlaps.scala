import scala.io.Source

case class Square(id: String, x: Int, y: Int, width: Int, length: Int)

val filename = "input"
val buf = scala.collection.mutable.ListBuffer.empty[Square]
for (line <- Source.fromFile(filename).getLines) {
    val coords = line.substring(line.indexOf("@ ") + 2, line.indexOf(": ")).split(",")
    val dims = line.substring(line.indexOf(": ") + 2).split("x")

    val square = Square(id = line.substring(0,
      line.indexOf("@") - 1),
      x = coords(0).toInt,
      y = coords(1).toInt,
      width = dims(0).toInt,
      length=dims(1).toInt)

    buf+=square
}

def hasOverlap(allSquares: List[Square], x: Int, y: Int): Boolean = {
  allSquares.filter(sq => {
    x < sq.x + sq.width && x >= sq.x && y < sq.y + sq.length && y >= sq.y
  }).size >= 2
}

def hasOverlap2(square: Square, allSquares: List[Square]): Boolean = {
  allSquares.filter(sq => {
    (sq.x  <= square.x && sq.x + sq.width - 1 >= square.x || square.x  <= sq.x && square.x + square.width - 1 >= sq.x) &&
    (sq.y  <= square.y && sq.y + sq.length - 1 >= square.y || square.y  <= sq.y && square.y + square.length - 1 >= sq.y)
  }).size == 1
}

val allSquares = buf.toList
var count = 0
(0 to 1200).foreach(x => {
  (0 to 1200).foreach(y => {
    if (hasOverlap(allSquares, x, y)) {
      count = count + 1
    }
  })
})

println(count)

allSquares.filter(sq => hasOverlap2(sq, allSquares))
