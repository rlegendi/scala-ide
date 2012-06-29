package scala.tools.eclipse.specs2

import scala.tools.eclipse.specs2.wizards.NewAcceptanceSpecificationWizard
import scala.tools.eclipse.specs2.wizards.NewUnitSpecificationWizard

object Specs2Plugin {
  def pluginId: String = "scala.tools.eclipse.specs2"
}

class Specs2Plugin {
  def accSpecs2WizId: String = classOf[NewAcceptanceSpecificationWizard].getName
  def unitSpecs2WizId: String = classOf[NewUnitSpecificationWizard].getName
}
