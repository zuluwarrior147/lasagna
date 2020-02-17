package lasagna

import scala.util.Random

object FeatureShuffler {

  type Features = List[(Int,Feature)]
  def splitFeatures(features: Features, testPercentage:Double = 0.2,trainingPercentage:Double = 0.8): (Features, Features) = {
    val shuffeled = Random.shuffle(features)
    val testSize =  Math.ceil(features.size * testPercentage)
    val trainIntermediate = Math.floor(features.size * trainingPercentage).toInt
    val trainSize = if(trainIntermediate +testSize != features.size) trainIntermediate-1 else trainIntermediate
    ((shuffeled.takeRight(trainSize), shuffeled.take(testSize.toInt)))
  }

  def transformMap(map: Map[Label, List[Feature]]): (Map[Label, Int], Features) = {
    val labelsCorrespondence = map.keySet.toSeq.zipWithIndex.toMap
    def explode(): List[(Int, Feature)] = {
      map.toList.flatMap(pair => {
        pair._2.map((labelsCorrespondence(pair._1), _))
      })
    }
    (labelsCorrespondence, explode())
  }
}