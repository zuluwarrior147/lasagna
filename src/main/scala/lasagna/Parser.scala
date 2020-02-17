package lasagna

object Parser {
  def parseLines(lines: List[String]): (Label,List[Feature]) = {

    def isValidFeatureLine(line:String):Boolean ={
      line.matches("\\s?-\\s?.+")
    }

    val label = parseLabel(lines.head)
    val features = lines.tail.withFilter(isValidFeatureLine).map(parseFeature)
    (label, features)
  }

  def parseFeature(line: String):Feature = {
    Feature(line.split("- ").last.trim)
  }

  def parseLabel(line: String): Label = {
    Label(line.split("## intent:").last.trim)
  }
}
