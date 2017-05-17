package prototype.link.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;

import prototype.link.api.Link;
import prototype.link.api.LinkDTO;
import prototype.link.api.LinkUtility;

@SuppressWarnings("restriction")
public class LinkUtilityImpl implements LinkUtility {

	@Inject Logger logger;

	/* (non-Javadoc)
	 * @see prototype.link.api.LinkUtility#updateFrom(org.eclipse.core.resources.IMarker, java.lang.Long)
	 */
	@Override
	public void updateFrom(IMarker marker, Long id) {
		update(marker, id, /*from*/true);
	}

	/* (non-Javadoc)
	 * @see prototype.link.api.LinkUtility#updateTo(org.eclipse.core.resources.IMarker, java.lang.Long)
	 */
	@Override
	public void updateTo(IMarker marker, Long id) {
		update(marker, id, /*from*/false);
	}

	protected void update(IMarker marker, Long id, boolean from) {
		final String direction = from ? Link.LINK_FROM : Link.LINK_TO;

		List<Long> results = get(marker, from);
		if (!results.contains(id)) {
			results.add(id);
			try {
				marker.setAttribute(direction, StringUtils.join(results, ","));
			} catch (CoreException e) {
				logger.warn(e);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see prototype.link.api.LinkUtility#getFrom(org.eclipse.core.resources.IMarker)
	 */
	@Override
	public List<Long> getFrom(IMarker marker) {
		return get(marker, /*from*/true);
	}

	/*
	 * (non-Javadoc)
	 * @see prototype.link.api.LinkUtility#getTo(org.eclipse.core.resources.IMarker)
	 */
	@Override
	public List<Long> getTo(IMarker marker) {
		return get(marker, /*from*/false);
	}
	
	protected List<Long> get(IMarker marker, boolean from) {
		final String direction = from ? Link.LINK_FROM : Link.LINK_TO;
		final List<Long> result = new ArrayList<>();
		try {
			if (marker.getType().equals(Link.LINK_TYPE)) {
				String value = marker.getAttribute(direction, "");
				result.addAll(parseAttribute(value));
			}
		} catch (CoreException e) {
			logger.warn(e);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see prototype.link.api.LinkUtility#validLinkDTO(prototype.link.api.LinkDTO)
	 */
	@Override
	public boolean validLinkDTO(LinkDTO dto) { 
		return dto != null && dto.resource != null && dto.resource.exists();
	}
	
	/*
	 * (non-Javadoc)
	 * @see prototype.link.api.LinkUtility#markerAtLocation(org.eclipse.core.resources.IResource, org.eclipse.jface.text.ITextSelection)
	 */
	@Override
	public IMarker getMarkerAtSelection(IResource resource, ITextSelection textSelection) {
		IMarker marker = null;

		final int charStart = textSelection.getOffset();
		final int charEnd = textSelection.getOffset() + textSelection.getLength();

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
	
	protected List<Long> parseAttribute(String value) {
		final List<Long> result = new ArrayList<>();
		if (value != null && !value.equals("") && !value.trim().equals("")) {
			value = value.trim();
			String[] splits = value.split(",");
			for (String s: splits) {
				s = s.trim();
				try {
					result.add(Long.parseLong(s));
				} catch (NumberFormatException e) {
					logger.warn(e);
				}
			}
		}
		return result;
	}

	protected int safeInteger(String character) {
		int result = -1;
		try {
			Integer.parseInt(character);
		} catch (NumberFormatException e) {
			logger.warn("Invalid character for expected integer value: " + character);
		}
		return result;
	}
}
