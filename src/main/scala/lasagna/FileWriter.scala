package lasagna

import java.io.{File, FileOutputStream}

trait Writer{
  def write(line:String):Writer
  def close():String
}

class FileWriter(fileName:String, lineSeparator:Option[String]=Some("\n")) extends Writer {

  private[this] val file  = new File(fileName)
  private[this] val fos = new FileOutputStream(file)

  def write(line:String): FileWriter ={
    fos.write(line.getBytes(CHARSET))
    lineSeparator.foreach(x=>fos.write(x.getBytes(CHARSET)))
    this
  }

  def close():String={
    fos.close()
    file.getAbsolutePath
  }

}

object FileWriter{
  def apply(fileName: String): FileWriter = new FileWriter(fileName)
}
