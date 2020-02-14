package lasagna

import scala.util.Random

object FeatureShuffler {

  type Features = List[(Int,Feature)]

  def splitFeatures(features: Features): (Features, Features) = {
    /*
    I while put a loop
    I will take variable till some counter exhausts or on of the portion is done

     */

    val shuffeled = Random.shuffle(features)

//    (0 until features.length).foldLeft()
    val testSize =  (features.size * 0.2).toInt
    val trainIntermediate = (features.size * 0.8).toInt
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
