package scala.tools.eclipse.specs2.wizards.pages

import org.osgi.framework.Bundle
import scala.tools.eclipse.specs2.Specs2Plugin
import scala.tools.eclipse.wizards.AbstractNewElementWizardPage

abstract class SpecsAbstractNewElementWizardPage extends AbstractNewElementWizardPage {
  override protected def getCurrentPluginBundle: Bundle = Specs2Plugin.getDefault.getBundle
}
