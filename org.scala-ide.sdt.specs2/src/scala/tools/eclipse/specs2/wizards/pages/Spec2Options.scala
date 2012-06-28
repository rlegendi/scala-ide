package scala.tools.eclipse.specs2.wizards.pages

import scala.tools.eclipse.wizards.AbstractNewElementWizardPage

import org.eclipse.jface.dialogs.IDialogSettings
import org.eclipse.swt.widgets.Composite

trait TSpec2Options { self: AbstractNewElementWizardPage =>

  def initializeOptions(dialogSettings: IDialogSettings): Unit = {
    methodStubButtons.enableSelectionButton(0, false)
    methodStubButtons.setSelection(0, false)

    methodStubButtons.enableSelectionButton(1, false)
    methodStubButtons.setSelection(1, false)

    methodStubButtons.enableSelectionButton(2, false)
    methodStubButtons.setSelection(2, false)
  }

  def specifyModifierControls(composite: Composite, columns: Int): Unit = {}
}
