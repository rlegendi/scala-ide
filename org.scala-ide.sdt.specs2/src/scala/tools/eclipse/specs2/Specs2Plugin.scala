package scala.tools.eclipse.specs2

import scala.tools.eclipse.specs2.wizards.NewAcceptanceSpecificationWizard
import scala.tools.eclipse.specs2.wizards.NewUnitSpecificationWizard

class Specs2Plugin {
  def pluginId: String = "scala.tools.eclipse.specs2"

  def accSpecs2WizId: String = classOf[NewAcceptanceSpecificationWizard].getName
  def unitSpecs2WizId: String = classOf[NewUnitSpecificationWizard].getName
}

object SpecificationWizard {

  private val TEMPLATE_ACCEPTANCE_SPEC =
    """object %s {
      |  def main(args: Array[String]) {
      |    
      |  }
      |}""".stripMargin

  private val TEMPLATE_UNIT_SPEC =
    """object %s extends App {
      |
      |}""".stripMargin

}
