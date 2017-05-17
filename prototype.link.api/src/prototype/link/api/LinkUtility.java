package prototype.link.api;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;

public interface LinkUtility {

	List<Long> getFrom(IMarker marker);

	List<Long> getTo(IMarker marker);

	void updateFrom(IMarker marker, Long id);
	
	void updateTo(IMarker marker, Long id);
	
	boolean validLinkDTO(LinkDTO dto); 

	IMarker getMarkerAtSelection(IResource resource, ITextSelection textSelection);
}
