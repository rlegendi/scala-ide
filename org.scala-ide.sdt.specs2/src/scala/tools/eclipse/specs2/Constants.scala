package scala.tools.eclipse.specs2

object Constants {
  val EoL = sys.props("line.separator")
  
  val TEMPLATE_ACCEPTANCE_SPEC = Utils.readContents("AcceptanceSpec.tpl")
//    """object %s {
//      |  def main(args: Array[String]) {
//      |    
//      |  }
//      |}""".stripMargin

  val TEMPLATE_UNIT_SPEC =
    """object %s extends App {
      |
      |}""".stripMargin
}
