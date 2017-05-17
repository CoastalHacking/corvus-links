 
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
import prototype.link.api.LinkMarkerDTO;
import prototype.link.api.LinkUtility;


public class AddHandler {

	@Inject LinkMarkerDTO markerDTO;
	
	@Inject LinkUtility linkUtility;

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) ITextSelection textSelection,
			IEditorPart editorPart) {

		final IResource resource = Adapters.adapt(editorPart.getEditorInput(), IResource.class);
		if (resource == null || !resource.exists()) {
			// TODO: debug log 
			return;
		}

		IMarker marker = linkUtility.getMarkerAtSelection(resource, textSelection);
		if (marker == null) {
			try {
				marker = resource.createMarker(Link.LINK_TYPE);
			} catch (CoreException e) {
				// TODO: debug log 
				e.printStackTrace();
				return;
			}

		}

		if (markerDTO.marker != null) {
			linkUtility.updateTo(markerDTO.marker, marker.getId());
			linkUtility.updateFrom(marker, markerDTO.marker.getId());
		}
		markerDTO.marker = marker;

	}
		
}