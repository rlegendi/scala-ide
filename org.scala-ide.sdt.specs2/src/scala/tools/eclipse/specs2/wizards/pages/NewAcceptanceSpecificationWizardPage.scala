package scala.tools.eclipse.specs2.wizards.pages

import scala.tools.eclipse.wizards.AbstractNewElementWizardPage
import org.eclipse.swt.widgets.Composite
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages
import org.eclipse.jdt.internal.corext.codemanipulation.StubUtility
import org.eclipse.jdt.core.IPackageFragment
import org.eclipse.core.runtime.SubProgressMonitor

class NewAcceptanceSpecificationWizardPage extends {
  val declarationType = "Acceptance Specification"
  // TODO Refactor DEFAULT_SUPER_TYPE
} with AbstractNewElementWizardPage with TSpec2Options {
  override def createControl(parent: Composite) = {
    super.createControl(parent)
    setSuperClass("org.specs2.Specification", false)
  }
}
