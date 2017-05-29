package prototype.link.ui.popup;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;

public class LinkLabelProvider {

	public static class LinkResourceLabelProvider extends LabelProvider implements IStyledLabelProvider {

		@Override
		public StyledString getStyledText(Object element) {
			if (element instanceof IMarker) {
				final IMarker marker = (IMarker)element;
				final StyledString styledString = new StyledString(
						String.format("%s", marker.getResource().getFullPath().toString()));
				return styledString;
			}
			return null;
		}

	}

	public static class LinkLocationLabelProvider extends LabelProvider implements IStyledLabelProvider {

		@Override
		public StyledString getStyledText(Object element) {
			if (element instanceof IMarker) {
				final IMarker marker = (IMarker)element;
				final int charStart = marker.getAttribute(IMarker.CHAR_START, -1);
				final int charEnd = marker.getAttribute(IMarker.CHAR_END, -1);
				final int lineNumber = marker.getAttribute(IMarker.LINE_NUMBER, -1);
				final StyledString styledString = new StyledString(
						String.format("Line %d: %d-%d", lineNumber, charStart, charEnd));
				return styledString;
			}
			return null;
		}

	}
	
	public static class LinkSelectionLabelProvider extends LabelProvider implements IStyledLabelProvider {

		@Override
		public StyledString getStyledText(Object element) {
			if (element instanceof IMarker) {
				final IMarker marker = (IMarker)element;
				final String message = marker.getAttribute(IMarker.MESSAGE, "");
				final StyledString styledString = new StyledString(String.format("%s", message));
				return styledString;
			}
			return null;
		}

	}
}
