package scala.tools.eclipse.specs2.launching

import scala.tools.eclipse.javaelements.ScalaSourceFile
import scala.tools.eclipse.javaelements.ScalaClassElement
import org.eclipse.jdt.core.IType

object Specs2LaunchShortcut {
  def isSpecificationStructure(iType: IType): Boolean = {
    val anns = iType.getAnnotations
    val ifaces = iType.getSuperInterfaceNames()
    val ret = ifaces.contains("org.specs2.specification.SpecificationStructure")
    
    if (ret) {
    	weaveWrappedWithAnnotation(iType)
    }
    
    ret
  }
  
  private def weaveWrappedWithAnnotation(t: IType): Unit = {
    val cf = t.getClassFile
    val fqn = t.getFullyQualifiedName
    println(fqn)
  }
    
  def containsSpecificationStructure(scSrcFile: ScalaSourceFile): Boolean = {
    val suiteOpt = scSrcFile.getAllTypes().find { tpe => tpe.isInstanceOf[ScalaClassElement] && isSpecificationStructure(tpe) }
    suiteOpt match {
      case Some(suite) => true
      case None => false
    }
  }
}
