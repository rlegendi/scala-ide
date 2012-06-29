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

class NewAcceptanceSpecificationWizard
  extends AbstractNewElementWizard(new NewAcceptanceSpecificationWizardPage) {

  private def createSource(applicationName: String, pkg: IPackageFragment): String = {
    val appExists = try { Class.forName("specs2.Specification"); true } catch { case _ => false }
    val packageDeclaration = if (pkg.isDefaultPackage) "" else "package " + pkg.getElementName + "" + Constants.EoL * 2
    val objectTemplate = Constants.TEMPLATE_ACCEPTANCE_SPEC
    val unformatted = packageDeclaration + objectTemplate.format(applicationName)
    ScalaFormatter.format(unformatted, FormatterPreferences.getPreferences(pkg.getResource.getProject))
  }

  private def createApplication(applicationName: String, pkg: IPackageFragment): Boolean = {
    val nameOk = applicationName.nonEmpty && Chars.isIdentifierStart(applicationName(0)) &&
      applicationName.tail.forall(Chars.isIdentifierPart)
    if (!nameOk) {
      wizardPage.setErrorMessage("Not a valid name.")
      return false
    }

    val file = pkg.getResource.asInstanceOf[IFolder].getFile(applicationName + ".scala")
    if (file.exists) {
      wizardPage.setErrorMessage("Resource with same name already exists.")
      return false
    }

    val source = createSource(applicationName, pkg)
    file.create(new StringBufferInputStream(source), true, null)
    openInEditor(file)

    //addLaunchConfig(applicationName, pkg)
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

  override def performFinish: Boolean =
    //Utils.tryExecute(createApplication(wizardPage.getApplicationName, wizardPage.getSelectedPackage)).getOrElse(false)
    Utils.tryExecute(createApplication(wizardPage.getTypeName, wizardPage.getPackageFragment)).getOrElse(false)

  /*
  //  override def performFinish(): Boolean = {
  //    val fileName = wizardPage.getTypeName
  //    
  //    val op = new IRunnableWithProgress() {
  //    }

  //        try {
  //            getContainer().run(true, false, op);
  //        } catch (InterruptedException e) {
  //            return false;
  //        } catch (InvocationTargetException e) {
  //            Throwable realException = e.getTargetException();
  //            MessageDialog.openError(getShell(), "Error", realException.getMessage());
  //            return false;
  //        }
  //        return true;
  //  }

  override def performFinish: Boolean = {

    // For handling the long operation
    val op = new IRunnableWithProgress() {
      override def run(monitor: IProgressMonitor): Unit = { // throws InvocationTargetException
        try {
          doFinish(monitor)
        } catch {
          case e: CoreException => throw new InvocationTargetException(e)
        } finally {
          monitor.done
        }
      }
    }

    // TODO Any way like this?
    //Utils.tryExecute(() => {getContainer().run(true, false, op); true}).getOrElse(false)

    try {
      getContainer().run(true, false, op)
      return true
    } catch {
      case e: InvocationTargetException =>
        MessageDialog.openError(getShell(), "Error", e.getTargetException.getMessage);
        return false
    }
  }

  private def doFinish(monitor: IProgressMonitor): Unit = {
    createApplication(wizardPage.getTypeName, wizardPage.getSelectedPackage)
  }

  private def createApplication(applicationName: String, pkg: IPackageFragment): Boolean = {
    val nameOk = applicationName.nonEmpty && Chars.isIdentifierStart(applicationName(0)) &&
      applicationName.tail.forall(Chars.isIdentifierPart)
    if (!nameOk) {
      wizardPage.setErrorMessage("Not a valid name.")
      return false
    }

    val file = pkg.getResource.asInstanceOf[IFolder].getFile(applicationName + ".scala")
    if (file.exists) {
      wizardPage.setErrorMessage("Resource with same name already exists.")
      return false
    }

    val source = createSource(applicationName, pkg)
    file.create(new StringBufferInputStream(source), true, null)
    openInEditor(file)
    addLaunchConfig(applicationName, pkg)
    true
  }
  */
}