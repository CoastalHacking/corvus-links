package prototype.link.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.services.log.Logger;

import prototype.link.api.Link;
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
}
