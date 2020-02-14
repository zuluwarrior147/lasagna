package lasagna

import java.io.File

import scala.io.Source.fromFile

object FileParser {
  def parseFile(file: File): Map[Label, List[Feature]] = {
    val linesItr = fromFile(file).getLines()

    def isLabel = (line: String) => line.trim.matches("## intent:\\s?\\w+")



    /*

    two accumulators --> one for intermediate list  -> One to store the result of Parsed map
     */

    final case class Aggregator(private val chunk: List[String] = List.empty, private val resultAcc: Map[Label, List[Feature]] = Map.empty) {
      def addLine(line: String): Aggregator = {
        if (isLabel(line)) {
          if (chunk.isEmpty) {
            this.copy(chunk = List(line))
          } else {
            val tuple: (Label, List[Feature]) = Parser.parseLines(chunk)
            copy(chunk = List(line), resultAcc = resultAcc.+(tuple))
          }
        } else {
          copy(chunk = chunk :+ line)
        }
      }

      def getFinalResult(): Map[Label, List[Feature]] = {
        if (chunk.size > 0) {
          resultAcc + (Parser.parseLines(chunk))
        } else {
          resultAcc
        }
      }
    }

    def chunker(aggregator: Aggregator): Map[Label, List[Feature]] = {
      if (linesItr.hasNext) {
        val line = linesItr.next()
        chunker(aggregator.addLine(line))
      } else {
        aggregator.getFinalResult()
      }
    }

    chunker(Aggregator())
  }

}
