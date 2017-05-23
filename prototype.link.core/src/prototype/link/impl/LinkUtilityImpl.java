package prototype.link.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.services.log.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import prototype.link.api.Link;
import prototype.link.api.LinkUtility;

@SuppressWarnings("restriction")
public class LinkUtilityImpl implements LinkUtility {

	public static final String CONTAINER_KEY = "container_path";
	public static final String RESOURCE_KEY = "resource_path";
	public static final String MARKER_KEY = "marker_id";

	@Inject Logger logger;


	/*
	 * (non-Javadoc)
	 * @see prototype.link.api.LinkUtility#markerAtLocation(org.eclipse.core.resources.IResource, org.eclipse.jface.text.ITextSelection)
	 */
	@Override
	public IMarker getMarkerAtSelection(IResource resource, int charStart, int charEnd) {
		IMarker marker = null;

		try {
			for (IMarker m: resource.findMarkers(Link.LINK_TYPE, /*includeSubtypes*/true, /*?*/ IResource.DEPTH_ONE)) {
				final int markerCharStart = (Integer)m.getAttribute(IMarker.CHAR_START, -1);
				final int markerCharEnd = (Integer)m.getAttribute(IMarker.CHAR_END, -1);

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

	/* (non-Javadoc)
	 * @see prototype.link.api.LinkUtility#getMarkers(org.eclipse.core.resources.IMarker, prototype.link.api.Link.DIRECTION)
	 */
	@Override
	public List<IMarker> getMarkers(IMarker marker, boolean from) {

		final IWorkspaceRoot root = marker.getResource().getWorkspace().getRoot();
		String attribute = getAttribute(from);

		final String jsonAttribute = marker.getAttribute(attribute, new JSONArray().toString());
		final List<IMarker> results = new ArrayList<>();

		try {
			final JSONArray jsonMarkers = new JSONArray(jsonAttribute);
			for (int i=0; i < jsonMarkers.length(); i++) {
				final JSONObject jsonObject = jsonMarkers.getJSONObject(i);
				final String containerString = jsonObject.getString(CONTAINER_KEY);
				final String resourceString = jsonObject.getString(RESOURCE_KEY);
				long markerId = jsonObject.getLong(MARKER_KEY);

				final IPath containerPath = Path.fromPortableString(containerString);
				if (containerPath == null)
					continue;

				final IResource resourceContainer = root.findMember(containerPath);
				final IContainer container = resourceContainer.getAdapter(IContainer.class);
				if (container == null)
					continue;

				final IPath resourcePath = Path.fromPortableString(resourceString);
				if (resourcePath == null)
					continue;
				
				final IResource resource = container.findMember(resourcePath);
				if (resource != null &&  resource.exists()) {
					IMarker m = resource.getMarker(markerId);
					if (m != null)
						results.add(m);
				}
			}
		} catch (JSONException e) {
			// TODO: log
			e.printStackTrace();
		}		

		return results;
	}

	/* (non-Javadoc)
	 * @see prototype.link.api.LinkUtility#updateMarker(org.eclipse.core.resources.IMarker, org.eclipse.core.resources.IMarker)
	 */
	@Override
	public void updateMarkers(IMarker from, IMarker to) {
		updateMarkers(from, Link.LINK_TO, to);
		updateMarkers(to, Link.LINK_FROM, from);

	}

	/*
	 * package-private for testability
	 */
	void updateMarkers(IMarker source, String attribute, IMarker object) {

//		String leftAttribute = left.getAttribute(attribute, new JSONArray().toString());
		JSONArray array = getJSONAttribute(source, attribute);
		try {
//			leftArray = new JSONArray(leftAttribute);
			array.put(toJSONObject(object));
			updateMarker(source, attribute, array);
			// IMarker setAttribute contract
//			String leftArrayString = leftArray.toString();
//			if (leftArrayString.getBytes(StandardCharsets.UTF_8).length < 65535) {
//				left.setAttribute(attribute, leftArrayString);
//			} else {
//				// TODO: log
//				System.out.println("The marker attribute length is too damn high");
//			}
		} catch (JSONException | CoreException e) {
			// TODO: log
			e.printStackTrace();
		}
	}

	void updateMarker(IMarker marker, String attribute, JSONArray array) throws CoreException {
		final String value = array.toString();
		if (value.getBytes(StandardCharsets.UTF_8).length < 65535) {
			marker.setAttribute(attribute, value);
		} else {
			// TODO: log
			System.out.println("The marker attribute length is too damn high");
		}

	}

	JSONObject toJSONObject(IMarker marker) throws JSONException {
		final JSONObject object =  new JSONObject();
		object.put(MARKER_KEY, marker.getId());

		final IResource resource = marker.getResource();
		final String resourcePath = resource.getProjectRelativePath().toPortableString();
		object.put(RESOURCE_KEY, resourcePath);

		final String containerPath = resource.getParent().getFullPath().toPortableString();
		object.put(CONTAINER_KEY, containerPath);

		return object;
	}

	/* (non-Javadoc)
	 * @see prototype.link.api.LinkUtility#hasLinks(org.eclipse.core.resources.IMarker, boolean)
	 */
	@Override
	public boolean hasLinks(IMarker marker, boolean from) {
		return getJSONAttribute(marker, getAttribute(from)).length() > 0;
	}

	String getAttribute(boolean from) {
		return from ? Link.LINK_FROM : Link.LINK_TO;
	}

	JSONArray getJSONAttribute(IMarker marker, boolean from) {
		return getJSONAttribute(marker, getAttribute(from));
	}
	
	JSONArray getJSONAttribute(IMarker marker, String attribute) {
//		String attribute = from? Link.LINK_FROM : Link.LINK_TO;
		final JSONArray emptyArray = new JSONArray();
		final String jsonAttribute = marker.getAttribute(attribute, emptyArray.toString());
		JSONArray result;
		try {
			result = new JSONArray(jsonAttribute);
		} catch (JSONException e) {
			// TODO: log
			e.printStackTrace();
			result = emptyArray;
		}
		return result;
	}

	
	/* (non-Javadoc)
	 * @see prototype.link.api.LinkUtility#breakLinks(org.eclipse.core.resources.IMarker)
	 */
	@Override
	public void breakLinks(IMarker marker) {

		for (boolean direction: new boolean[]{true, false}) {
			for (IMarker m: getMarkers(marker, direction)) {
				try {
					// invert the direction, since the subject is now the object and vice versa
					removeLink(m, !direction, marker);
				} catch (JSONException | CoreException e) {
					// TODO log
					e.printStackTrace();
				}
			}
		}
		
	}

	/*
	 * returns true if link removed
	 */
	boolean removeLink(IMarker subject, boolean from, IMarker object) throws JSONException, CoreException {
		JSONArray array = getJSONAttribute(subject, from);
		boolean removed = false;

		final JSONObject removeObject = toJSONObject(object);
		
		int removeIndex = -1;
		for (int i=0; i<array.length(); i++) {

			if (equalLinks(array.getJSONObject(i), removeObject)) {
				removeIndex = i;
				break;
			}
		}
		if (removeIndex != -1) {
			removed = true;
			array.remove(removeIndex);
			updateMarker(subject, getAttribute(from), array);
		}
		return removed;
	}
	
	boolean equalLinks(JSONObject subject, JSONObject object) {
		boolean result;
		try {
			result = subject.getString(CONTAINER_KEY).equals(object.getString(CONTAINER_KEY));
			result &= subject.getString(RESOURCE_KEY).equals(object.getString(RESOURCE_KEY));
			result &= subject.getString(MARKER_KEY).equals(object.getString(MARKER_KEY));
		} catch (JSONException e) {
			result = false;
		}
		return result;
	}

}
