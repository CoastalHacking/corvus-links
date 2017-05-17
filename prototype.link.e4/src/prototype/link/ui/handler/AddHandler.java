 
package prototype.link.ui.handler;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;

import prototype.link.api.Link;
import prototype.link.api.LinkContext;
import prototype.link.api.LinkUtility;


public class AddHandler {

	@Inject LinkContext linkContext;
	
	@Inject LinkUtility linkUtility;

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) ITextSelection textSelection,
			IEditorPart editorPart) {

		final IResource resource = Adapters.adapt(editorPart.getEditorInput(), IResource.class);
		if (resource == null || !resource.exists()) {
			// TODO: debug log 
			return;
		}

		final int charStart = textSelection.getOffset();
		final int charEnd = textSelection.getOffset() + textSelection.getLength();
		final int lineNumber = textSelection.getStartLine();
		
		// FIXME: execute logic via IWorkspace.run
		IMarker marker = linkUtility.getMarkerAtSelection(resource, charStart, charEnd);
		if (marker == null) {
			try {
				marker = resource.createMarker(Link.LINK_TYPE);
				marker.setAttribute(IMarker.CHAR_START, charStart);
				marker.setAttribute(IMarker.CHAR_END, charEnd);
				marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
			} catch (CoreException e) {
				// TODO: debug log 
				e.printStackTrace();
				return;
			}

		}

		if (linkContext.marker != null) {
			linkUtility.updateTo(linkContext.marker, marker.getId());
			linkUtility.updateFrom(marker, linkContext.marker.getId());
		}
		linkContext.marker = marker;

	}
		
}