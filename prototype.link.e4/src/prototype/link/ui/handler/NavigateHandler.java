package prototype.link.ui.handler;

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

import prototype.link.api.Link;
import prototype.link.api.LinkUtility;
import prototype.link.ui.popup.LinkPopupDialog;

public class NavigateHandler {
	
	@Inject LinkUtility linkUtility;

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
			@Named(IServiceConstants.ACTIVE_SELECTION) ITextSelection textSelection,
			IEditorPart editorPart,
			IWorkbenchPage page,
			@Named("prototype.link.ui.commandparameter.navigate") String direction) {

		boolean from = Link.BACKWARDS.equals(direction);

		final IResource resource = Adapters.adapt(editorPart.getEditorInput(), IResource.class);

		if (editorPart instanceof ITextEditor) {
			// http://stackoverflow.com/a/40736560
			final ITextEditor editor = (ITextEditor) editorPart;
			StyledText text = (StyledText)editor.getAdapter(Control.class);
			if (text != null) {
				int caret = text.getCaretOffset();
				Point point = text.getLocationAtOffset(caret);
				point = text.toDisplay(point);

				IMarker marker = linkUtility.getMarkerAtSelection(resource,
						textSelection.getOffset(),
						textSelection.getOffset() + textSelection.getLength());
				
				PopupDialog popupDialog = getPopupDialog(shell, page, marker, point, from);
				popupDialog.open();
			}
		}	
	}

	private PopupDialog getPopupDialog(Shell shell, IWorkbenchPage page, IMarker marker,
			Point point, boolean from) {
		return new LinkPopupDialog(shell, page, marker, point, linkUtility, from);
	}
}
