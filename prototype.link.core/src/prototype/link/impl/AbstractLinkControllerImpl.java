package prototype.link.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.util.EcoreUtil;

import prototype.link.api.Link;
import prototype.link.api.Link.Direction;
import prototype.link.api.LinkController;
import prototype.link.model.Container;
import prototype.link.model.LinkMarker;
import prototype.link.model.LinksFactory;
import prototype.link.model.Marker;
import prototype.link.model.Resource;
import prototype.link.model.WorkspaceRoot;

public abstract class AbstractLinkControllerImpl implements LinkController {

	// Set by subclass
	protected WorkspaceRoot root;
	protected IWorkspace workspace;

	
	protected final Object lock = new Object();
	protected AtomicReference<LinkMarker> atomicLastLinkMarker = new AtomicReference<>();

	
	/* (non-Javadoc)
	 * @see prototype.link.api.LinkController#addLink(org.eclipse.core.resources.IMarker)
	 */
	@Override
	public void addLink(IMarker subject) {

			addOrContinueLink(subject, /*create*/true);
	}

	/* (non-Javadoc)
	 * @see prototype.link.api.LinkController#continueLink(org.eclipse.core.resources.IMarker)
	 */
	@Override
	public void continueLink(IMarker subject) {

		addOrContinueLink(subject, /*create*/false);
		
	}
	
	/* (non-Javadoc)
	 * @see prototype.link.api.LinkController#changedLink(org.eclipse.core.resources.IMarker)
	 */
	@Override
	public void modifyLink(IMarker subject) {
		final int charStart = subject.getAttribute(IMarker.CHAR_START, -1);
		final int charEnd = subject.getAttribute(IMarker.CHAR_END, -1);
		final int lineNumber = subject.getAttribute(IMarker.LINE_NUMBER, -1);
		final String message = subject.getAttribute(IMarker.MESSAGE, "");

		synchronized (lock) {
			LinkMarker linkMarker = getOrCreateLinkMarkerNoLock(subject, /*create*/false);

			if (charStart != linkMarker.getCharStart()) linkMarker.setCharStart(charStart);
			if (charEnd != linkMarker.getCharEnd()) linkMarker.setCharEnd(charEnd);
			if (lineNumber != linkMarker.getLineNumber()) linkMarker.setLineNumber(lineNumber);
			if (!message.equals(linkMarker.getMessage())) linkMarker.setMessage(message);
		}
	}

	/* (non-Javadoc)
	 * @see prototype.link.api.LinkController#endLink()
	 */
	@Override
	public void endLink() {
		atomicLastLinkMarker.set(null);

	}

	/* (non-Javadoc)
	 * @see prototype.link.api.LinkController#removeLink(org.eclipse.core.resources.IMarker)
	 */
	@Override
	public void removeLink(IMarker subject) {
		final long id = subject.getId();
		final IResource iResource = subject.getResource();
		final String resourcePath = iResource.getProjectRelativePath().toPortableString();
		final String containerPath = iResource.getParent().getFullPath().toPortableString();

		// TODO: refactor to use an EMF command and transaction
		// Otherwise EMF requires external synchronization
		synchronized (lock) {
			Container container = null;
			for (Container c: root.getContainers()) {
				if (c.getPortableFullPath() != null && c.getPortableFullPath().equals(containerPath)) {
					container = c;
				}
			}
	
			if (container == null) {
				// TODO: we're deleting, these things should exist; log
				return;
			}

			prototype.link.model.Resource resource = null;
			for (prototype.link.model.Resource r: container.getResources()) {
				if (r.getPortableProjectRelativePath() != null &&
						r.getPortableProjectRelativePath().equals(resourcePath)) {
					resource = r;
				}
			}
			
			if (resource == null) {
				// TODO: log
				return;
			}
			
			LinkMarker linkMarker = null;
			for (Marker m: resource.getMarkers()) {
				if (m.getId() == id && m instanceof LinkMarker) {
					linkMarker = (LinkMarker)m;
				}
			}

			if (linkMarker == null) {
				// TODO: log
				return;
			}

			// Remove empty leaves; may incur performance issues
			// @see http://eclipsesource.com/blogs/2015/05/26/emf-dos-and-donts-11/
			EcoreUtil.delete(linkMarker, true);

			if (resource.getMarkers().isEmpty()) {
				EcoreUtil.delete(resource, true);
			}
			
			if (container.getResources().isEmpty()) {
				EcoreUtil.delete(container, true);
			}
			
		}
		
	}

	/* (non-Javadoc)
	 * @see prototype.link.api.LinkController#getMarkers(org.eclipse.core.resources.IMarker, boolean)
	 */
	@Override
	public List<IMarker> getMarkers(IMarker subject, Direction direction) {
		
		List<IMarker> results = new ArrayList<>();

		synchronized (lock) {
			results = getMarkersNoLock(
					getLinkMarkersNoLock(subject, direction));
		}

		return results;
	}

	/* (non-Javadoc)
	 * @see prototype.link.api.LinkController#getMarkerAtSelection(org.eclipse.core.resources.IResource, int, int)
	 *
	 */
	@Override
	public IMarker getMarkerAtSelection(IResource resource, int charStart, int charEnd) {
		IMarker marker = null;

		try {
			for (IMarker m: resource.findMarkers(Link.LINK_TYPE, /*includeSubtypes*/true, /*?*/ IResource.DEPTH_ONE)) {
				// ends are exclusive; starts are inclusive

				final int markerCharStart = (Integer)m.getAttribute(IMarker.CHAR_START, -1);
				final int markerCharEnd = (Integer)m.getAttribute(IMarker.CHAR_END, -1);

				// ignore if the subject marker starts before this one ends
				if (markerCharEnd < charStart)
					continue;

				// ignore if the subject marker ends before this one starts
				if (charEnd < markerCharStart)
					continue;

				// keep lowest resource-relative ID
				if (marker == null || marker.getId() > m.getId())
					marker = m;
				
			}
		} catch (CoreException e) {
			// TODO: log
			e.printStackTrace();
		}

		// TODO: verify the marker is in the model graph w/o locking
		return marker;

	}

	/* (non-Javadoc)
	 * @see prototype.link.api.LinkController#hasMarkers(org.eclipse.core.resources.IMarker, prototype.link.api.Link.Direction)
	 */
	@Override
	public boolean hasMarkers(IMarker subject, Direction direction) {
		boolean result;
		synchronized (lock) {
			result = (!getLinkMarkersNoLock(subject, direction).isEmpty());
		}
		return result;
	}

	List<LinkMarker> getLinkMarkersNoLock(IMarker subject, Direction direction) {
		LinkMarker linkMarker = getOrCreateLinkMarkerNoLock(subject, /*create*/false);

		List<LinkMarker> linkMarkers;
		switch (direction) {
		case FROM:
			linkMarkers = linkMarker.getFrom();
			break;
		case TO:
			linkMarkers = linkMarker.getTo();
			break;
		default:
			// should be unreachable
			throw new IllegalArgumentException("Invalid direction: " + direction);
		}
		return linkMarkers;
	}
	
	LinkMarker getOrCreateLinkMarkerNoLock(IMarker subject, boolean create) {
		final long id = subject.getId();
		final int charStart = subject.getAttribute(IMarker.CHAR_START, -1);
		final int charEnd = subject.getAttribute(IMarker.CHAR_END, -1);
		final int lineNumber = subject.getAttribute(IMarker.LINE_NUMBER, -1);
		final String message = subject.getAttribute(IMarker.MESSAGE, "");
		final IResource iResource = subject.getResource();
		final String resourcePath = iResource.getProjectRelativePath().toPortableString();
		final String containerPath = iResource.getParent().getFullPath().toPortableString();
		LinkMarker result = null;
		
		Container container = null;
		for (Container c: root.getContainers()) {
			if (c.getPortableFullPath() != null && c.getPortableFullPath().equals(containerPath)) {
				container = c;
			}
		}

		if (container == null) {
			if (create) {
				container = LinksFactory.eINSTANCE.createContainer();
				root.getContainers().add(container);
				container.setPortableFullPath(containerPath);
			} else {
				return result;
			}
		}
		
		prototype.link.model.Resource resource = null;
		final List<Resource> resources = container.getResources();
		for (prototype.link.model.Resource r: resources) {
			if (r.getPortableProjectRelativePath() != null &&
					r.getPortableProjectRelativePath().equals(resourcePath)) {
				resource = r;
			}
		}
		
		if (resource == null) {
			if (create) {
				resource = LinksFactory.eINSTANCE.createResource();
				resource.setPortableProjectRelativePath(resourcePath);
				resources.add(resource);
			} else {
				return result;
			}
		}
		
		
		final List<Marker> markers = resource.getMarkers();
		for (Marker m: markers) {
			if (m.getId() == id && m instanceof LinkMarker) {
				result = (LinkMarker)m;
			}
		}

		if (result == null) {
			if (create) {
				result = LinksFactory.eINSTANCE.createLinkMarker();
				result.setId(id);
				result.setCharStart(charStart);
				result.setCharEnd(charEnd);
				result.setLineNumber(lineNumber);
				result.setMessage(message);
				markers.add(result);
			} else {
				return result;
			}
		}
		return result;

	}
	
	List<IMarker> getMarkersNoLock(List<LinkMarker> linkMarkers) {
		List<IMarker> results = new ArrayList<>();
		final IWorkspaceRoot workspaceRoot = workspace.getRoot();
		for (LinkMarker lm: linkMarkers) {
			long markerId = lm.getId();
			String resourcePath = lm.getResource().getPortableProjectRelativePath();
			String containerPath = lm.getResource().getContainer().getPortableFullPath();
			final IResource containerResource = workspaceRoot.findMember(containerPath);
			final IContainer container = containerResource.getAdapter(IContainer.class);
			final IResource iResource = container.findMember(resourcePath);
			IMarker iMarker = iResource.getMarker(markerId);
			if (iMarker != null) {
				results.add(iMarker);
			}
		}
		
		return results;
	}
	
	void addOrContinueLink(IMarker subject, boolean create) {
		// TODO: refactor to use an EMF command and transaction
		// Otherwise EMF requires external synchronization
		synchronized (lock) {

			LinkMarker linkMarker = getOrCreateLinkMarkerNoLock(subject, /*create*/true);

			if (linkMarker == null)
				return;
			
			final LinkMarker lastLinkMarker = atomicLastLinkMarker.get();
			
			// don't continue on same marker
			if (lastLinkMarker == linkMarker)
				return;

			if (lastLinkMarker != null) {
				linkMarker.getFrom().add(lastLinkMarker);
				lastLinkMarker.getTo().add(linkMarker);
			}

			// And replace last marker if it wasn't replaced
			atomicLastLinkMarker.compareAndSet(lastLinkMarker, linkMarker);
		}
	}

}
