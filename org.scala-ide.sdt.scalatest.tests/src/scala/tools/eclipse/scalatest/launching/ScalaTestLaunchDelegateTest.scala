package scala.tools.eclipse.scalatest.launching

import org.junit.Test
import org.junit.Assert._

class ScalaTestLaunchDelegateTest {
  @Test(expected = classOf[IllegalArgumentException])
  def testEscapingForNullClassPathEntry() {
    // TODO These might be static methods in a companion object
    def delegate = new ScalaTestLaunchDelegate
    delegate.escapeScalaTestClasspathComponent(null)
  }

  @Test
  def testEscapingForEmptyClassPathEntry() {
    def delegate = new ScalaTestLaunchDelegate
    def res = delegate.escapeScalaTestClasspathComponent("")
    assertEquals("", res)
  }

  @Test
  def testEscapingForSimpleClassPathEntry() {
    def delegate = new ScalaTestLaunchDelegate
    def res = delegate.escapeScalaTestClasspathComponent("some.jar")
    assertEquals("some.jar", res)
  }

  @Test
  def testEscapingForClassPathEntryWithSpaces() {
    def delegate = new ScalaTestLaunchDelegate

    def res = delegate.escapeScalaTestClasspathComponent("Program files")
    assertEquals("Program\\ files", res)
  }

  @Test
  def testEscapingForClassPathEntryWithDoubleQuotes() {
    def delegate = new ScalaTestLaunchDelegate

    def res = delegate.escapeScalaTestClasspathComponent("middle\"escaped")
    assertEquals("middle\\\"escaped", res)
  }
}