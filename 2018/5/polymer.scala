import scala.io.Source

val polymer = Source.fromFile("input").getLines.next
react(polymer).length

('a' to 'z')
  .map(c => polymer.replaceAll(s"(?i)${c}", ""))
  .map(react(_))
  .minBy(_.length)
  .length

def react(polymer: String): String = {
  var pol = polymer
  var i = 0
  while (i < pol.length - 1) {
    if (pol(i) != pol(i + 1) && pol(i).toLower == pol(i + 1).toLower) {
      pol = pol.substring(0, i) + pol.substring(i + 2)
      i = 0
    }
    else i = i + 1
  }
  pol
}