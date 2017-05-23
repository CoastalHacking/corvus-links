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
import org.eclipse.core.runtime.Path;

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

	
	private final Object lock = new Object();
	private AtomicReference<LinkMarker> atomicLastLinkMarker = new AtomicReference<>();

	
	/* (non-Javadoc)
	 * @see prototype.link.api.LinkController#addLink(org.eclipse.core.resources.IMarker)
	 */
	@Override
	public void addLink(IMarker subject) {
		
		// TODO: refactor to use an EMF command and transaction
		// Otherwise EMF requires external synchronization
		synchronized (lock) {

			LinkMarker linkMarker = getOrCreateLinkMarkerNoLock(subject, /*create*/true);
	
			// Now link with last marker
			final LinkMarker lastLinkMarker = atomicLastLinkMarker.get();
			if (lastLinkMarker != null) {
				linkMarker.getFrom().add(lastLinkMarker);
				lastLinkMarker.getTo().add(linkMarker);
			}

			// And replace last marker if it wasn't replaced
			atomicLastLinkMarker.compareAndSet(lastLinkMarker, linkMarker);
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

			// Remove empty leaves
			resource.getMarkers().remove(linkMarker);

			if (resource.getMarkers().isEmpty()) {
				container.getResources().remove(resource);
			}
			
			if (container.getResources().isEmpty()) {
				root.getContainers().remove(container);
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
			results = getMarkersNoLock(getLinkMarkersNoLock(subject, direction));
		}

		return results;
	}

	/* (non-Javadoc)
	 * @see prototype.link.api.LinkController#getMarkerAtSelection(org.eclipse.core.resources.IResource, int, int)
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
			// TODO: log
			e.printStackTrace();
		}
		
		
		// TODO: verify the marker is in the model graph	
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
			final IResource iResource = container.findMember(new Path(resourcePath));
			IMarker iMarker = iResource.getMarker(markerId);
			if (iMarker != null) {
				results.add(iMarker);
			}
		}
		
		return results;
	}
}
