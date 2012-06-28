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

  /*
  override def createType(progressMonitor: IProgressMonitor): Unit = {
    val monitor = if (progressMonitor == null) new NullProgressMonitor() else progressMonitor
    
    val MONITOR_STEPS = 8
    
    monitor.beginTask(NewWizardMessages.NewTypeWizardPage_operationdesc, MONITOR_STEPS)
    
    implicit val packageFragment = {
      val rt = getPackageFragmentRoot
      val pf = getPackageFragment
      var p = pf match {
        case ipf: IPackageFragment => ipf
        case _ => rt.getPackageFragment("")
      }
      p.exists match {
        case true => monitor.worked(1)
        case _ => p = rt.createPackageFragment(pf.getElementName, true,
          new SubProgressMonitor(monitor, 1))
      }
      p
    }
    
    implicit val ld = StubUtility.getLineDelimiterUsed(
      packageFragment.getJavaProject)
  }
      val typeName = getTypeNameWithoutParameters
    val cuName = getCompilationUnitName(typeName)
*/
    
}
