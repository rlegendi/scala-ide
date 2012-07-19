package scala.tools.eclipse.specs2.wizards

import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jface.text.templates.Template
import org.eclipse.jdt.internal.ui.JavaPlugin
import org.eclipse.jdt.internal.ui.viewsupport.ProjectTemplateStore
import java.io.IOException
import org.eclipse.jdt.internal.corext.template.java.CodeTemplateContextType
import org.eclipse.jdt.internal.corext.template.java.CodeTemplateContext
import org.eclipse.jdt.internal.corext.codemanipulation.StubUtility
import org.eclipse.jdt.core.Signature
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.Status
import org.eclipse.jdt.internal.corext.util.Strings

object TemplateUtils {
  def getFileTemplate(project: IJavaProject): Option[String] =
    Option(getCodeTemplateText(CodeTemplateContextType.FILECOMMENT_ID, project))

  def getTypeTemplate(project: IJavaProject): Option[String] =
    Option(getCodeTemplateText(CodeTemplateContextType.TYPECOMMENT_ID, project))

  private def getCodeTemplateText(id: String, project: IJavaProject): String = {
    val template =
      if (project == null) {
        JavaPlugin.getDefault().getCodeTemplateStore().findTemplateById(id)
      } else {
        val projectStore = new ProjectTemplateStore(project.getProject())

        try {
          projectStore.load();
        } catch {
          case e: IOException => JavaPlugin.log(e)
        }

        projectStore.findTemplateById(id);
      }

    // TODO Change EoL definition to this
    val ld = StubUtility.getLineDelimiterUsed(project)
    val context = new CodeTemplateContext(template.getContextTypeId(), project, ld);

    // TODO
    //context.setVariable(CodeTemplateContextType.ENCLOSING_TYPE, Signature.getQualifier(typeQualifiedName));
    //context.setVariable(CodeTemplateContextType.TYPENAME, Signature.getSimpleName(typeQualifiedName));

    try {
      val buffer = context.evaluate(template)
      val str = buffer.getString();
      if (Strings.containsOnlyWhitespaces(str)) {
        return null;
      } else {
        str
      }
      /*
			val position = findVariable(buffer, CodeTemplateContextType.TAGS); // look if Javadoc tags have to be added
			if (position == null) {
				return str;
			}
	
			IDocument document= new Document(str);
			int[] tagOffsets= position.getOffsets();
			for (int i= tagOffsets.length - 1; i >= 0; i--) { // from last to first
				try {
					insertTag(document, tagOffsets[i], position.getLength(), EMPTY, EMPTY, null, typeParameterNames, false, lineDelim);
				} catch (BadLocationException e) {
					throw new CoreException(JavaUIStatus.createError(IStatus.ERROR, e));
				}
			}
			return document.get();
			*/
    } catch {
      case _ => throw new CoreException(Status.CANCEL_STATUS)
    }
  }
}