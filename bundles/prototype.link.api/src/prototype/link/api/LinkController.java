package prototype.link.api;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;

import prototype.link.api.Link.Direction;

public interface LinkController {

	/**
	 * 
	 * Add a link to a marker
	 * 
	 * If the marker contains a link, no operations occur
	 * 
	 * @param subject  the marker being linked 
	 */
	void addLink(IMarker subject);
	
	/**
	 * Continues, or un-ends, a link on a marker
	 * 
	 * @param subject
	 */
	void continueLink(IMarker subject);

	/**
	 * Ends a chain of links
	 */
	void endLink();

	/**
	 * Disconnect all links to and from this marker
	 * 
	 * @param subject
	 */
	void removeLink(IMarker subject);
	
	/**
	 * Modifies previously unset values via a marker
	 * 
	 * Non-existent, empty strings, or -1 integer values are considered un-set
	 * 
	 * @param subject
	 */
	void modifyLink(IMarker subject);

	/**
	 * 
	 * Given a marker and a direction, list out all linked markers
	 * 
	 * @param subject
	 * @param direction
	 * @return
	 */
	List<IMarker> getMarkers(IMarker subject, Direction direction);
	
	/**
	 * 
	 * Given a marker and a direction, identify if there are any linked markers
	 * 
	 * @param subject
	 * @param direction
	 * @return
	 */
	boolean hasMarkers(IMarker subject, Direction direction);
	
	/**
	 * Given a resource and a selection range, identify if any markers are linked
	 * 
	 * If more than one link marker exists within the range, the marker with the lowest
	 * ID is chosen.
	 * 
	 * @param resource
	 * @param charStart
	 * @param charEnd
	 * @return
	 */
	IMarker getMarkerAtSelection(IResource resource, int charStart, int charEnd);
}
