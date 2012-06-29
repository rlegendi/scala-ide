package scala.tools.eclipse.specs2.wizards

import java.io.ByteArrayInputStream
import scala.tools.eclipse.specs2.Constants
import scala.tools.eclipse.wizards.AbstractNewElementWizard
import scala.tools.eclipse.wizards.AbstractNewElementWizardPage
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IFolder
import org.eclipse.jdt.core.IPackageFragment
import org.eclipse.ui.ide.IDE
import scalariform.formatter.ScalaFormatter
import scala.tools.eclipse.formatter.FormatterPreferences
import scala.tools.eclipse.util.Utils

// TODO Generate comments ain't working
abstract class ASpecificationWizard(val templateName: String, override val wizardPage: AbstractNewElementWizardPage)
  extends AbstractNewElementWizard(wizardPage) {
  
  val TEMPLATE: String = scala.tools.eclipse.specs2.Utils.readContents(templateName)
  
  private def createSource(applicationName: String, pkg: IPackageFragment): String = {
    val packageDeclaration = if (pkg.isDefaultPackage) "" else "package " + pkg.getElementName + "" + Constants.EoL * 2
    val templateSource = TEMPLATE
    val unformatted = packageDeclaration + templateSource.format(applicationName)
    ScalaFormatter.format(unformatted, FormatterPreferences.getPreferences(pkg.getResource.getProject))
  }

  private def createApplication(applicationName: String, pkg: IPackageFragment): Boolean = {
    val file = pkg.getResource.asInstanceOf[IFolder].getFile(applicationName + ".scala")
    val source = createSource(applicationName, pkg)
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
