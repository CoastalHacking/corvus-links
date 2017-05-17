package prototype.link.impl;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.log.Logger;

import prototype.link.api.Link;
import prototype.link.api.LinkController;
import prototype.link.api.LinkDTO;
import prototype.link.api.LinkDTOSet;
import prototype.link.api.LinkUtility;

@SuppressWarnings("restriction")
public class LinkControllerImpl implements LinkController {

	@Inject Logger logger;
	
	@Inject LinkUtility linkUtility;
	
	@PostConstruct
	void construct(IEclipseContext context) {
		context.set(LinkDTOSet.class, new LinkDTOSet());
	}
	
	@Override
	public void popup() {
	}

	@Override
	public void start(LinkDTO linkDTO) {
		add(linkDTO);
	}

	@Override
	public void add(LinkDTO linkDTO) {
		
	}

	@Override
	public void end(LinkDTO linkDTO) {
	}


	@Override
	public void cancel() {
	}

	private void addOrUpdateMarkers(LinkDTO linkDTO) {
		IMarker marker = markerAtLocation(linkDTO.resource,
				linkDTO.textSelectionDTO.offset,
				linkDTO.textSelectionDTO.offset + linkDTO.textSelectionDTO.length);

		// FIXME: Encapsulate all changes via resource.getWorkspace().run(...)
		if (marker == null) {
			try {
				marker = linkDTO.resource.createMarker(Link.LINK_TYPE);
			} catch (CoreException e) {
				logger.warn(e);
			}
		}
		
		if (marker != null) {
			// TODO:
		}
	}

	private IMarker markerAtLocation(IResource resource, int charStart, int charEnd) {
		IMarker marker = null;

		try {
			for (IMarker m: resource.findMarkers(Link.LINK_TYPE, /*includeSubtypes*/true, /*?*/ IResource.DEPTH_ONE)) {
				final int markerCharStart = safeInteger((String)m.getAttribute(IMarker.CHAR_START, "-1"));
				final int markerCharEnd = safeInteger((String)m.getAttribute(IMarker.CHAR_END, "-1"));

				// if the start character is after this end character, skip
				if (markerCharStart > charEnd)
					continue;
				
				// if the end character is before this start character, skip
				if (markerCharEnd < charStart)
					continue;

				// keep lowest resource-relative ID
				if (marker == null || marker.getId() > m.getId())
					marker = m;
			}
		} catch (CoreException e) {
			logger.warn(e);
		}
		return marker;
	}

	private int safeInteger(String character) {
		int result = -1;
		try {
			Integer.parseInt(character);
		} catch (NumberFormatException e) {
			logger.warn("Invalid character for expected integer value: " + character);
		}
		return result;
	}
}
