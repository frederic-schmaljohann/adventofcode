import java.io.File
import java.io.InputStream

fun main(args : Array<String>) {
  val inputStream: InputStream = File("input.txt").inputStream()
	val lineList = mutableListOf<String>()

	inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }
	println(lineList.map{it.toInt()}.sum())
}
