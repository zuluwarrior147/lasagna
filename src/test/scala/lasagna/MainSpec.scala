package lasagna

import java.io.File

class MainSpec extends UnitSpec {

  "Main class " should {
    "receives the input and then generates the output in out location" in {
      val file = InputFileFixture.createInputFile()
      val outDir = "./src/test/resources/testOutPut"
      val args = Array("-i", file.getAbsolutePath, "-o", outDir)

      Main.main(args)

      val outDirFile = new File(outDir)
      outDirFile.listFiles().filter(_.isFile).size should be(3)
      outDirFile
        .listFiles()
        .filter(_.isFile)
        .map(_.getName) should contain only ("train.csv", "test.csv", "labels.csv")
      file.delete()
    }
  }
}
