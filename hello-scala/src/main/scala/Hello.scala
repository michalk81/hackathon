import java.io.File
import java.nio.file.Files
import java.util

import scala.io.{BufferedSource, Source}

object Hello {

  def main(args: Array[String]): Unit = {
    val fileContent: Array[Char] = Source.fromFile(new File(args(0)), "UTF-8")
      .getLines()
      .mkString(System.getProperty("line.separator"))
      .toCharArray

    val frequencies = resolveFrequencies(fileContent)


    val bitSet: util.BitSet = new util.BitSet(4)
    bitSet.set(0, true)
    bitSet.set(1, false)
    bitSet.set(2, true)
    bitSet.set(3, false)

    val outputFile = new File(args(1))
    Files.write(outputFile.toPath, bitSet.toByteArray)

    val encoded: Array[Byte] = Files.readAllBytes(outputFile.toPath)


  }

  def resolveFrequencies(chars: Array[Char]): Map[Char, Int] = {
    chars.groupBy(identity).mapValues(_.size)
  }

}
