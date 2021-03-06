package us.coastalhacking.corvus.link.ui.handler;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.texteditor.ITextEditor;

import us.coastalhacking.corvus.link.api.LinkController;
import us.coastalhacking.corvus.link.api.Link.Direction;
import us.coastalhacking.corvus.link.ui.popup.LinkPopupDialog;

public class NavigateHandler {
	
	@Inject LinkController linkController;

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
			@Named(IServiceConstants.ACTIVE_SELECTION) ITextSelection textSelection,
			IEditorPart editorPart,
			IWorkbenchPage page,
			@Named("us.coastalhacking.corvus.link.ui.commandparameter.navigate") String directionParam) {

		Direction direction = "backwards".equals(directionParam) ? Direction.FROM : Direction.TO;

		final IResource resource = Adapters.adapt(editorPart.getEditorInput(), IResource.class);

		if (editorPart instanceof ITextEditor) {
			// http://stackoverflow.com/a/40736560
			final ITextEditor editor = (ITextEditor) editorPart;
			StyledText text = (StyledText)editor.getAdapter(Control.class);
			if (text != null) {
				int caret = text.getCaretOffset();
				Point point = text.getLocationAtOffset(caret);
				point = text.toDisplay(point);

				int charStart = textSelection.getOffset();
				int charEnd = charStart + textSelection.getLength();

				// On Mac cannot trust an empty text selection
				if (textSelection.getLength() == 0) {
					charStart = charEnd = caret;
				}

				IMarker marker = linkController.getMarkerAtSelection(resource, charStart, charEnd);
				
				PopupDialog popupDialog = getPopupDialog(shell, page, marker, point, direction);
				popupDialog.open();
			}
		}	
	}

	private PopupDialog getPopupDialog(Shell shell, IWorkbenchPage page, IMarker marker,
			Point point, Direction direction) {
		return new LinkPopupDialog(shell, page, marker, point, linkController, direction);
	}
}
