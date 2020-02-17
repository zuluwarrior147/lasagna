package lasagna

import java.io.File

import lasagna.DataWriter._

object Main {
  val labelsFile = "labels.csv"
  val testFile = "test.csv"
  val trainFile = "train.csv"

  def main(args: Array[String]): Unit = {
    val params = CmdParser.parse(args)


    val labelToFeatures = FileParser.parseFile(new File(params.inputFile))
    val (labels, features) = FeatureShuffler.transformMap(labelToFeatures)
    val (trainFeatures, testFeatures) = FeatureShuffler.splitFeatures(features)
    val labelsWriter = FileWriter(s"${params.outputLocation}/${labelsFile}")
    val trainWriter = FileWriter(s"${params.outputLocation}/${trainFile}")
    val testWriter = FileWriter(s"${params.outputLocation}/${testFile}")

    writeAll(labels.toSeq, labelsWriter)
    writeAll(trainFeatures, trainWriter)
    writeAll(testFeatures, testWriter)

    labelsWriter.close()
    trainWriter.close()
    testWriter.close()
  }

}
