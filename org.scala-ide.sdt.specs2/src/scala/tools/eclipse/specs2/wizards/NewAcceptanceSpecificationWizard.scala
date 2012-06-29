package scala.tools.eclipse.specs2.wizards

import scala.tools.eclipse.specs2.Utils
import scala.tools.eclipse.specs2.wizards.pages.NewAcceptanceSpecificationWizardPage

// TODO Generate comments ain't working
class NewAcceptanceSpecificationWizard
  extends ASpecificationWizard("AcceptanceSpec.tpl", new NewAcceptanceSpecificationWizardPage)
