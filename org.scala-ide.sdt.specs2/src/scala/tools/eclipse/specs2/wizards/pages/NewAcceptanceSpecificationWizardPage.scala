package scala.tools.eclipse.specs2.wizards.pages

import scala.tools.eclipse.wizards.AbstractNewElementWizardPage
import org.eclipse.swt.widgets.Composite

class NewAcceptanceSpecificationWizardPage extends {
  val declarationType = "Acceptance Specification"
  // TODO Refactor DEFAULT_SUPER_TYPE
} with AbstractNewElementWizardPage
  with TSpec2Options {
  override def createControl(parent: Composite): Unit = {
    super.createControl(parent)
    setSuperClass("org.specs2.Specification", false)
  }
}
