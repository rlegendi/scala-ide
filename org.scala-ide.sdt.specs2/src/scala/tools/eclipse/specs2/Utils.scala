package scala.tools.eclipse.specs2

import java.net.URI
import java.io.File
import File.{ pathSeparator => / }

object Utils {
  def readFileContents(fname: String): String = {
    //val url = Utils.getClass.getResource("templates" + File.separator + fname)
    val url = Specs2Plugin.getDefault.pathInBundle("/resources/wizard-templates/" + fname)

    url match {
      case Some(x) =>
        val source = scala.io.Source.fromFile(x.toFile.toURI)
        val lines = source.mkString
        source.close()

        lines

      case None => return "// Error: Specs2 templates missing. Cannot create a sample specification, sry :S" + Constants.EoL +
        "// Searched URL was: " + url
    }
  }
}
