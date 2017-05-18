package prototype.link.ui.popup;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;

public class LinkLabelProvider extends LabelProvider implements IStyledLabelProvider {

	@Override
	public StyledString getStyledText(Object element) {
		if (element instanceof IMarker) {
			IMarker marker = (IMarker)element;
			StyledString styledString = new StyledString(String.format("link %d", marker.getId()));
			return styledString;
		}
		return null;
	}

}
