package scala.tools.eclipse.specs2

import java.net.URI
import java.io.File
import File.{ pathSeparator => / }
import org.eclipse.core.resources.IFolder
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.resources.IFile

object Specs2Utils {
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

  def createParentDirectories(file: IFile): Unit = {
    require(file != null)

    createRecursively(file.getParent.asInstanceOf[IFolder])
  }

  def createRecursively(folder: IFolder, monitor: IProgressMonitor = null): Unit = {
    require(folder != null)

    val force = true // How to handle out-of-sync resources
    val local = true

    if (folder.exists()) {
      return ;
    }

    if (!folder.getParent().exists()) {
      createRecursively(folder.getParent().asInstanceOf[IFolder], monitor);
    }

    folder.create(force, true, monitor);
  }
}
