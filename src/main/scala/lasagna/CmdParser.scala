package lasagna

import java.io.File

case class CMDParams(
  inputFile:String ="",
  outputLocation:String ="",
  testDataPercentage:Option[Double] = None,
  trainingDataPercentage:Option[Double] = None
                    ){

}
object CmdParser {

  private def parserFormat()={
    val parser = new scopt.OptionParser[CMDParams]("Lasagna") {
      head("Lasagna", "0.1")
      opt[String]('i', "in").action((x, c) =>
        c.copy(inputFile = x)).required().text("Input file is required.")

      opt[String]('o', "out").required().valueName("<Output dir>").
        action((x, c) =>{
          val dir = new File(x)
          if(!dir.exists()){
            dir.mkdir()
          }
          c.copy(outputLocation = x)
        }).
        text("output directory is a required.")

      opt[Double]('t', "testPercentage")
        .action((x, c) => c.copy(testDataPercentage = Some(x)))
        .text("Test data split percentage can be provided")


      opt[Double]('r', "trainingPercentage")
        .action((x, c) => c.copy(trainingDataPercentage = Some(x)))
        .text("Training data split percentage can be provided")

    }
    parser
  }
  def parse(args: Array[String]): CMDParams = {
    parserFormat().parse(args,CMDParams()).getOrElse(throw new IllegalArgumentException())
  }

}
