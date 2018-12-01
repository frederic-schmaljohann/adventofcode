def frequency(changes: List[Int]) = changes.sum

def firstStateRepeated(changes: List[Int]): Unit = {
  var statesSeen = Set[Int]()

  var current = 0

  while (true) {
    var changesList = changes
    changesList.foreach(it => {
      current = current + it
      if (statesSeen.contains(current)) {
        println(current)
        throw new Exception()
      }
      statesSeen = statesSeen + current
      })
  }
}
