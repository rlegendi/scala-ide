package scala.tools.eclipse.specs2

import scala.tools.eclipse.specs2.wizards.NewAcceptanceSpecificationWizard
import scala.tools.eclipse.specs2.wizards.NewUnitSpecificationWizard

class Specs2Plugin {
  def accSpecs2WizId: String = classOf[NewAcceptanceSpecificationWizard].getName
  def unitSpecs2WizId: String = classOf[NewUnitSpecificationWizard].getName
}

object Specs2Plugin {
  def pluginId: String = "scala.tools.eclipse.specs2"
}
