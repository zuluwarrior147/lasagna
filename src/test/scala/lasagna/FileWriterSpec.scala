package lasagna

import scala.collection.mutable
import scala.io.Source

class FileWriterSpec extends UnitSpec {

  "write files" should {
    "write file with inout data to specified location" in {
      val input = Seq("1,label1", "0,label0")
      val writer = FileWriter("./src/test/resources/tempFile")
      input.foreach(x => {
        writer.write(x)
      })
      val file = writer.close()
      val content: String = Source.fromFile(file).getLines().mkString("\n")
      content should be(input.mkString("\n"))
    }
  }

  "stringify" should {
    "convert label pair to correct string representation" in {
      val input = (Label("l1"), 1)
      val actualOutput = DataWriter.stringifyLabel(input)
      val expectedOutput = "1,l1"
      actualOutput should be(expectedOutput)
    }
    "covert feature pair to correct representation" in {
      val input: (Int, Feature) = (1, Feature("f1"))
      val actualOutput = DataWriter.stringifyFeature(input)
      val expectedOutput = "1,f1"
      actualOutput should be(expectedOutput)
    }
  }

  "writeAll" should {
    "write all data from the sequence" in {
      val features = Seq(
        (1, Feature("f1")),
        (2, Feature("f2")),
        (3, Feature("f3"))
      )
      val outputList = mutable.ArrayBuffer[String]()
      val customWriter: Writer = new Writer {
        override def write(line: String): Writer = {
          outputList.append(line)
          this
        }
        override def close(): String = "???"
      }
      import DataWriter._
      writeAll(features, customWriter)

      features.map(DataWriter.stringifyFeature) should contain only (outputList: _*)

    }
  }

}
