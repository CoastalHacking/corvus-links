package prototype.link.api;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;

public interface LinkDTOFactory {

	public LinkDTO create(IEditorPart editorPart, ITextSelection textSelection);
}
