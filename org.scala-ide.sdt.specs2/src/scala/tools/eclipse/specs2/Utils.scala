package scala.tools.eclipse.specs2

import java.net.URI
import java.io.File
import File.{ pathSeparator => / }

object Utils {
  def readFileContents(fname: String): String = {
    val url = Utils.getClass.getResource("templates" + File.separator + fname)

    if (null == url) {
      return "// Error: Specs2 templates missing. Cannot create a sample specification, sry :S"
    }

    val source = scala.io.Source.fromFile(url.toURI)
    val lines = source.mkString
    source.close()

    lines
  }
}
