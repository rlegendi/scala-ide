package scala.tools.eclipse.specs2

object Constants {
  val EoL = sys.props("line.separator")
  
  val TEMPLATE_ACCEPTANCE_SPEC = Utils.readContents("AcceptanceSpec.tpl")
  val TEMPLATE_UNIT_SPEC = Utils.readContents("UnitSpec.tpl")
}
