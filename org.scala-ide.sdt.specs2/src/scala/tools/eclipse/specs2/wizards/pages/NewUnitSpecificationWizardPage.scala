package scala.tools.eclipse.specs2.wizards.pages

import scala.tools.eclipse.wizards.AbstractNewElementWizardPage
import org.eclipse.swt.widgets.Composite

class NewUnitSpecificationWizardPage extends {
  val declarationType = "Unit Specification"
} with AbstractNewElementWizardPage
  with TSpec2Options {
  override def createControl(parent: Composite): Unit = {
    super.createControl(parent)
    setSuperClass("org.specs2.mutable.Specification", false)
  }
}
