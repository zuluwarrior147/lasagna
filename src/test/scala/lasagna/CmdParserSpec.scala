package lasagna


class CmdParserSpec extends UnitSpec {

  "Command Paser" should {
    "return error when args are empty" in {
      val args = Array[String]()
      try {
        val cmdPAram = CmdParser.parse(args)
        true should be(false)
      }catch{
        case x:IllegalArgumentException => true should be (true)
      }

    }

    "should return correct in and out location" in {
      val args = Array("-i","input","-o","output")

      val CMDParams = CmdParser.parse(args)
      CMDParams.inputFile should be ("input")
      CMDParams.outputLocation should be ("output")
    }

    "should parse all params correctly" in {
      val args = Array("-i","input","-o","output","-t","0.2","-r","0.8")

      val CMDParams = CmdParser.parse(args)
      CMDParams.inputFile should be ("input")
      CMDParams.outputLocation should be ("output")
      CMDParams.testDataPercentage.get should be (0.2)
      CMDParams.trainingDataPercentage.get should be (0.8)
    }
  }

}
