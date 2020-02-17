package lasagna

import java.io.{File, PrintWriter}
import java.util.UUID

object InputFileFixture {

  def createInputFile(): File ={
    val lines =
      """## intent:label1
        | - Feature1
        | - Feature2
        |
        |## intent:label2
        | - Feature3
        | - Feature4
        |""".stripMargin

    val file  = new File(s"${UUID.randomUUID()}")
    new PrintWriter(file) {
      write(lines); close
    }
    file
  }

}
