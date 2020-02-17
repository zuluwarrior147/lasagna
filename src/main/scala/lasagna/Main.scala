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

    printlnStats(testFeatures.size,trainFeatures.size,labels.size)
    labelsWriter.close()
    trainWriter.close()
    testWriter.close()
  }

  def printlnStats(testSize: Int, trainingSize: Int, labelsSize: Int): Unit = {
    println(
      s"""
         Lasagna successfully generated the data.
         It produced Test Data size : ${testSize}
         Total Training data        : ${trainingSize}
         Total Labels               : ${labelsSize}
        """)
  }

}
