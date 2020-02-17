package lasagna

import org.scalatest.{Matchers, WordSpec}

class FeatureShufflerSpec extends WordSpec with Matchers {

  "Feature Shuffel " should {
    "return empty result " in {
      val map = Map(
        Label("label1") -> List(Feature("f1"),Feature("f2")),
        Label("label2") -> List(Feature("f3"),Feature("f4")))

      val labelFeatureTup: (Map[Label, Int ], List[(Int,Feature)] ) = FeatureShuffler.transformMap(map)

      labelFeatureTup._1.size should be(2)
      labelFeatureTup._1.toSeq should contain only(map.keySet.toSeq.zipWithIndex:_*)
      labelFeatureTup._2 should contain only (List((0, Feature("f1")),(0, Feature("f2")),(1, Feature("f3")),(1, Feature("f4"))):_*)
    }

    "split features to 80/20 lists" in {
      val features = List(
        (1, Feature("Feature1")),
        (1, Feature("Feature2")),
        (0, Feature("Feature3")),
        (0, Feature("Feature4")),
        (1, Feature("Feature5"))
      )

      val splittedFeatures = FeatureShuffler.splitFeatures(features)
      splittedFeatures._1.size should be(4)
      splittedFeatures._2.size should be(1)
      splittedFeatures._1.toSet.intersect(splittedFeatures._2.toSet).size should be(0)
    }
  }

}
