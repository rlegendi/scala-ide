package scala.tools.eclipse.specs2

import java.net.URI
import java.io.File

object Utils {
  def readContents(fname: String): String = {
    val cl = Utils.getClass
    val url = cl.getResource(fname)
    val uri = url.toURI
    
    val file = new File(uri)
    val fp = file.getAbsolutePath
    
    val source = scala.io.Source.fromFile(uri)
    val lines = source.mkString
    source.close()

    lines
  }
}
