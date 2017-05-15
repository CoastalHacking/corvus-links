package prototype.link.api;

import java.util.List;

import org.eclipse.core.resources.IMarker;

public interface LinkUtility {

	List<Long> getFrom(IMarker marker);

	List<Long> getTo(IMarker marker);

	void updateFrom(IMarker marker, Long id);
	
	void updateTo(IMarker marker, Long id);
}
