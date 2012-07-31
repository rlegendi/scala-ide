package scala.tools.eclipse.specs2.launching

import org.eclipse.core.expressions.PropertyTester
import scala.tools.eclipse.scalatest.launching.ScalaTestLaunchShortcut
import org.eclipse.jdt.core.IJavaElement
import org.eclipse.ui.part.FileEditorInput
import org.eclipse.core.runtime.IAdaptable
import scala.tools.eclipse.javaelements.ScalaSourceFile

class ScalaTestSpecs2FileTester extends PropertyTester {
  def test(receiver: Object, property: String, args: Array[Object], expectedValue: Object): Boolean = {
    try {
      receiver match {
        case scSrcFile: ScalaSourceFile => 
          Specs2LaunchShortcut.containsSpecificationStructure(scSrcFile)
        case editorInput: FileEditorInput => 
          if(receiver.isInstanceOf[IAdaptable]) {
            val je = receiver.asInstanceOf[IAdaptable].getAdapter(classOf[IJavaElement]).asInstanceOf[IJavaElement]
            je.getOpenable match {
              case scSrcFile: ScalaSourceFile => 
                Specs2LaunchShortcut.containsSpecificationStructure(scSrcFile)
              case _ => false
            }
          }
          else
            false
        case _ =>
          false
      }
    }
    catch {
      case _ => false
    }
  }
}
