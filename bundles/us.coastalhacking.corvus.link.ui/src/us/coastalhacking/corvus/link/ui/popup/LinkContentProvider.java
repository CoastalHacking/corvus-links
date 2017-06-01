package us.coastalhacking.corvus.link.ui.popup;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.ITreeContentProvider;

import us.coastalhacking.corvus.link.api.LinkController;
import us.coastalhacking.corvus.link.api.Link.Direction;

public class LinkContentProvider implements ITreeContentProvider {

	private LinkController linkController;
	private Direction direction;
		
	public LinkContentProvider(LinkController linkController, Direction direction) {
		super();
		this.linkController = linkController;
		this.direction = direction;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof IMarker) {
			final IMarker marker = (IMarker)inputElement;
			return linkController.getMarkers(marker, direction).toArray();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IMarker) {
			final IMarker marker = (IMarker)parentElement;
			return linkController.getMarkers(marker, direction).toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		boolean result = false;
		if (element instanceof IMarker) {
			final IMarker marker = (IMarker)element;
//			return linkUtility.hasLinks(marker, from);
			result = linkController.hasMarkers(marker, direction);
		}
		return result;
	}

}
