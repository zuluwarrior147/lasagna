package lasagna

import java.io.{File, PrintWriter}

class ParseSpec extends UnitSpec {
  "parseLabel" should {
    "return a parsed inform_id label" in {
      val actual = Parser.parseLabel("## intent:inform_id")
      val expected = Label("inform_id")

      actual shouldBe expected
    }

    "return a parsed greetings label" in {
      val actual = Parser.parseLabel("## intent:greetings")
      val expected = Label("greetings")

      actual shouldBe expected
    }


  }

  "ParseLines" should {
    "return pair with labels and feature" in {
      val lines =
        """## intent:label1
          | - Feature1
          | - Feature2
          |""".stripMargin.split("\n").toList

      val result = Parser.parseLines(lines)
      result._1 shouldBe Label("label1")
      result._2 should contain only(Feature("Feature1"), Feature("Feature2"))
    }
    "return pairs with labels and few invalid feature" in {
      val lines =
        """## intent:label1
          | - Feature1
          | - Feature2
          |
          | -
          |""".stripMargin.split("\n").toList

      val result = Parser.parseLines(lines)
      result._1 shouldBe Label("label1")
      result._2 should contain only(Feature("Feature1"), Feature("Feature2"))
    }


    "return set of pairs with labels" in {
      val file = InputFileFixture.createInputFile()

      val result = FileParser.parseFile(file)
      result.get(Label("label1")).isDefined shouldBe true
      result.get(Label("label2")).isDefined shouldBe true
      result(Label("label1")) should contain only(Feature("Feature1"), Feature("Feature2"))
      result(Label("label2")) should contain only(Feature("Feature3"), Feature("Feature4"))
      file.delete()
      //result._2 should
    }
  }

  "parseFeature" should {
    "return a parsed foo feature" in {
      val actual = Parser.parseFeature("- foo")
      val expected = Feature("foo")

      actual shouldBe expected
    }

    "return a parsed bar feature" in {
      val actual = Parser.parseFeature("- bar")
      val expected = Feature("bar")

      actual shouldBe expected
    }
  }
}
