import scala.collection.mutable.ArrayBuffer
import scala.io.Source

def execute(): Unit = {
  val filename = "input.txt"
  val codes = Source.fromFile(filename).getLines.toList
  val program = codes(0).split(",").map(i => i.toInt)
  
  program(1) = 12
  program(2) = 2

  var index = 0
  while (program(index) != 99) {
    program(index) match {
      case 1 => program(program(index+3)) = program(program(index+2)) + program(program(index+1))
      case 2 => program(program(index+3)) = program(program(index+2)) * program(program(index+1))
    }
    index = index + 4
    program.foreach(println)
  }
  println(program(0))  
}


def execute2(): Unit = {
  val filename = "input.txt"
  var codes = Source.fromFile(filename).getLines.toList
  var program = codes(0).split(",").map(i => i.toInt)
  var noun = 0
  var verb = 0

  while (noun < 100) {
    while (verb < 100) {
      program(1) = noun
      program(2) = verb
  
      var index = 0
      while (program(index) != 99) {
        program(index) match {
          case 1 => program(program(index+3)) = program(program(index+2)) + program(program(index+1))
          case 2 => program(program(index+3)) = program(program(index+2)) * program(program(index+1))
        }
        index = index + 4
      }
      if (program(0) == 19690720)
        println(100 * noun + verb)
    
        codes = Source.fromFile(filename).getLines.toList
        program = codes(0).split(",").map(i => i.toInt)  
        verb = verb + 1
    }
    verb = 0
    noun = noun + 1
  }
}