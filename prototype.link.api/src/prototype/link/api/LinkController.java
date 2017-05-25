package prototype.link.api;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;

import prototype.link.api.Link.Direction;

public interface LinkController {

	void addLink(IMarker subject);
	
	void continueLink(IMarker subject);

	void endLink();

	void removeLink(IMarker subject);

	List<IMarker> getMarkers(IMarker subject, Direction direction);
	
	boolean hasMarkers(IMarker subject, Direction direction);
	
	IMarker getMarkerAtSelection(IResource resource, int charStart, int charEnd, int lineNumber);
}
