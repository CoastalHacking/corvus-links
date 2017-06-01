package us.coastalhacking.corvus.link.impl;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import us.coastalhacking.corvus.link.api.Link;
import us.coastalhacking.corvus.link.api.LinkController;

@Component(immediate=true, service=LinkResourceListenerComponent.class)
public class LinkResourceListenerComponent implements IResourceChangeListener {
	
	private LinkController linkController;

	@Reference
	void setLinkController(LinkController linkController) {
		this.linkController = linkController;
	}
	void unsetLinkController(LinkController linkController) {
		this.linkController = null;
	}
	
	@Reference
	void setWorkspace(IWorkspace workspace) {
		workspace.addResourceChangeListener(this);
	}
	void unsetWorkspace(IWorkspace workspace) {
		workspace.removeResourceChangeListener(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		switch (event.getType()) {
		case IResourceChangeEvent.POST_CHANGE:
			final IMarkerDelta[] deltas = event.findMarkerDeltas(Link.LINK_TYPE, false);
			if (deltas != null && deltas.length != 0) {
				for (IMarkerDelta delta : deltas) {
					final IMarker marker = delta.getMarker();
					switch (delta.getKind()) {
					case (IResourceDelta.ADDED):
						handleMarkerAdd(marker);
						break;
					case (IResourceDelta.CHANGED):
						handleMarkerChange(marker);
						break;
					case (IResourceDelta.REMOVED):
						handleMarkerDelete(marker);
						break;
					default:
						// should be dead code
						throw new IllegalArgumentException("Unsupported delta kind: " + delta.getKind());
					}
				}
			}

			break;
		default:
			break;
		}
	}

	protected void handleMarkerDelete(IMarker marker) {
		linkController.removeLink(marker);
	}
	
	protected void handleMarkerAdd(IMarker marker) {
		linkController.addLink(marker);
	}
	
	protected void handleMarkerChange(IMarker marker) {
		linkController.modifyLink(marker);
	}
}
