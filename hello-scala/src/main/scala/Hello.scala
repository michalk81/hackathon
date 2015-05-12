import java.io.File

import scala.io.Source

object Hello {

  def main(args: Array[String]): Unit = {
    Source.fromFile(new File(args(0)), "UTF-8")
  }

}
