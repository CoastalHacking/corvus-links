 
package prototype.link.e4.handler;

import javax.inject.Named;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;


public class AddLinkHandler {
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) ITextSelection textSelection,
			IEditorPart editorPart) {

		IResource resource = Adapters.adapt(editorPart.getEditorInput(), IResource.class);
		
		if (editorPart instanceof ITextEditor) {
			ITextEditor textEditor = (ITextEditor)editorPart;

			if (textEditor != null && resource != null) {
				System.out.println("boom");
			}
		}
	}
		
}