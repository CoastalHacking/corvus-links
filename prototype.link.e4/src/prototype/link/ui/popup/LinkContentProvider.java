package prototype.link.ui.popup;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.ITreeContentProvider;

import prototype.link.api.LinkUtility;

public class LinkContentProvider implements ITreeContentProvider {

	private LinkUtility linkUtility;
	private boolean from;
	
	public LinkContentProvider(LinkUtility linkUtility, boolean from) {
		super();
		this.linkUtility = linkUtility;
		this.from = from;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof IMarker) {
			final IMarker marker = (IMarker)inputElement;
			return linkUtility.getMarkers(marker, from).toArray();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IMarker) {
			final IMarker marker = (IMarker)parentElement;
			return linkUtility.getMarkers(marker, from).toArray();
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
		if (element instanceof IMarker) {
			final IMarker marker = (IMarker)element;
			return linkUtility.hasLinks(marker, from);
		}
		return false;
	}

}
