package lasagna

object DataWriter {
  implicit def stringifyLabel(input: (Label, Int)) = s"${input._2},${input._1.id}"

  implicit def stringifyFeature(input: (Int, Feature)):String = s"${input._1},${input._2.value}"

  def writeAll[A,B](input: Seq[(A,B)], writer: Writer)(implicit stringifier: ((A, B)) => String):Unit = {
    input.foldLeft(writer)((acc, element)=>{
      acc.write(stringifier(element))
    })
  }
}
