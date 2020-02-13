package lasagna

import org.scalatest.{Matchers, WordSpec}

class LibrarySpec extends WordSpec with Matchers {
  "failing test" should {
    "fail" in {
      val actual = false
      val expected = true

      actual shouldBe expected
    }
  }
}
