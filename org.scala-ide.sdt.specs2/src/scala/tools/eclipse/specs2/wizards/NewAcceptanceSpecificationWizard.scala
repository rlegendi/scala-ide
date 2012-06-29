package scala.tools.eclipse.specs2.wizards

import scala.tools.eclipse.wizards.AbstractNewElementWizard
import scala.tools.eclipse.specs2.wizards.pages.NewAcceptanceSpecificationWizardPage
import org.eclipse.jdt.core.IPackageFragment
import org.eclipse.core.resources.IFolder
import scala.tools.nsc.util.Chars
import scala.tools.eclipse.util.Utils
import java.io.StringBufferInputStream
import org.eclipse.jface.operation.IRunnableWithProgress
import java.lang.reflect.InvocationTargetException
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.CoreException
import org.eclipse.jface.dialogs.MessageDialog
import scala.tools.eclipse.specs2.Constants
import scalariform.formatter.ScalaFormatter
import scala.tools.eclipse.formatter.FormatterPreferences
import org.eclipse.core.resources.IFile
import org.eclipse.ui.ide.IDE
import java.io.ByteArrayInputStream

// TODO Generate comments ain't working
class NewAcceptanceSpecificationWizard
  extends AbstractNewElementWizard(new NewAcceptanceSpecificationWizardPage) {

  private def createSource(applicationName: String, pkg: IPackageFragment): String = {
    val packageDeclaration = if (pkg.isDefaultPackage) "" else "package " + pkg.getElementName + "" + Constants.EoL * 2
    val templateSource = Constants.TEMPLATE_ACCEPTANCE_SPEC
    val unformatted = packageDeclaration + templateSource.format(applicationName)
    ScalaFormatter.format(unformatted, FormatterPreferences.getPreferences(pkg.getResource.getProject))
  }

  private def createApplication(applicationName: String, pkg: IPackageFragment): Boolean = {
    val file = pkg.getResource.asInstanceOf[IFolder].getFile(applicationName + ".scala")
    val source = createSource(applicationName, pkg)
    //file.create(new StringBufferInputStream(source), true, null)
    file.create(new ByteArrayInputStream(source.getBytes), true, null)
    openInEditor(file)

    true
  }

  // TODO This code segment is identical to NewApplicationWizard.openInEditor(), should it be put into a common superclass?
  private def openInEditor(file: IFile) = {
    selectAndReveal(file)
    for {
      workbenchWindow <- Option(getWorkbench.getActiveWorkbenchWindow)
      page <- Option(workbenchWindow.getActivePage)
    } IDE.openEditor(page, file, true)
  }

  override def performFinish: Boolean = {
    Utils.tryExecute(createApplication(wizardPage.getTypeName, wizardPage.getPackageFragment)).getOrElse(false)
  }
}
