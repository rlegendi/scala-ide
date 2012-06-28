package scala.tools.eclipse.specs2.wizards

import scala.tools.eclipse.wizards.AbstractNewElementWizard
import scala.tools.eclipse.specs2.wizards.pages.NewAcceptanceSpecificationWizardPage
import org.eclipse.jdt.core.IPackageFragment
import org.eclipse.core.resources.IFolder
import scala.tools.nsc.util.Chars
import scala.tools.eclipse.util.Utils
import java.io.StringBufferInputStream

class NewAcceptanceSpecificationWizard
  extends AbstractNewElementWizard(new NewAcceptanceSpecificationWizardPage) {
  
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
    Utils.tryExecute(createApplication(wizardPage.getTypeName, page.getSelectedPackage)).getOrElse(false)
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
}