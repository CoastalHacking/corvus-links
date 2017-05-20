package prototype.link.api;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;

public interface LinkUtility {

	List<IMarker> getMarkers(IMarker marker, boolean from);

	void updateMarkers(IMarker from, IMarker to);

	IMarker getMarkerAtSelection(IResource resource, int charStart, int charEnd);
	
	boolean hasLinks(IMarker marker, boolean from);
}
