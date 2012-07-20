package scala.tools.eclipse.specs2

import scala.tools.eclipse.specs2.wizards.NewAcceptanceSpecificationWizard
import scala.tools.eclipse.specs2.wizards.NewUnitSpecificationWizard
import org.eclipse.ui.plugin.AbstractUIPlugin
import org.osgi.framework.BundleContext
import org.eclipse.core.runtime.IPath
import org.osgi.framework.Bundle
import org.eclipse.core.runtime.FileLocator
import org.eclipse.core.runtime.Path

object Specs2Plugin {
  def pluginId: String = "scala.tools.eclipse.specs2"

  // TODO This is ugly as hell, any better approach?
  private var plugin: Specs2Plugin = _

  def getDefault: Specs2Plugin = {
    require(plugin != null)

    plugin
  }
}

class Specs2Plugin extends AbstractUIPlugin {
  def accSpecs2WizId: String = classOf[NewAcceptanceSpecificationWizard].getName
  def unitSpecs2WizId: String = classOf[NewUnitSpecificationWizard].getName

  /**
   * (non-Javadoc)
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  override def start(context: BundleContext): Unit = {
    super.start(context)
    Specs2Plugin.plugin = this
  }

  /**
   * (non-Javadoc)
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  override def stop(context: BundleContext): Unit = {
    Specs2Plugin.plugin = null
    super.stop(context)
  }
  
  def pathInBundle(path: String) : Option[IPath] = {
    val url = FileLocator.find(getBundle, Path.fromPortableString(path), null)
    Option(url) map { x => Path.fromOSString(FileLocator.toFileURL(x).getPath) }
  }
}
