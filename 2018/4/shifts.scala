import scala.io.Source

case class Shift(guardId: String, minutes: List[Int])
case class Event(guardId: String, fallsAsleep: Boolean, start: String)

val filename = "input"
val entries = scala.collection.mutable.ListBuffer.empty[String]
for (line <- Source.fromFile(filename).getLines) {
  entries += line
}

val entriesSorted = entries.toList.sorted

var currentGuard = entriesSorted(0).substring(entriesSorted(0).indexOf("]") + 2)

val events = entriesSorted.map(it => {
  if (it.contains("begins shift")) {
    currentGuard = it.substring(it.indexOf("]") + 2).replace("begins shift", "").replace("Guard #", "").trim()
  }
  Event(guardId = currentGuard,
        fallsAsleep = it.endsWith("asleep"),
        start = it.substring(1, it.indexOf("]")))
})

val byGuardId = events.sliding(2).flatMap(it => {
  if (it(0).fallsAsleep) {
    List(Shift(guardId = it(0).guardId,
    minutes = ((it(0).start.substring(it(0).start.length-2).toInt until (it(1).start.substring(it(1).start.length-2)).toInt).toList)))
  }
  else None
}).toList.groupBy(it => it.guardId)

//part 1
val mostMinutes = byGuardId.map(it =>
  (it._1,
    it._2.map(z => z.minutes)))
    .map(it => (it._1, it._2.foldLeft(List[Int]())(_ ++ _)))
    .map(it => (it._1, it._2.groupBy(identity).maxBy(_._2.size)._1))
    .maxBy(_._2)

println(mostMinutes._1.toInt * mostMinutes._2)

//part 2
val mostFrequentMinute = byGuardId.map(it =>
  (it._1,
    it._2.map(z => z.minutes)))
    .map(it => (it._1, it._2.foldLeft(List[Int]())(_ ++ _)))
    .map(it => (it._1, it._2.groupBy(identity).maxBy(_._2.size)))
    .maxBy(_._2._2.size)

println(mostFrequentMinute._1.toInt * mostFrequentMinute._2._1)
