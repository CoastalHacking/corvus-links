package prototype.link.ui.popup;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

import prototype.link.api.Link.Direction;
import prototype.link.api.LinkUtility;

public class LinkPopupDialog extends PopupDialog {

	private Point textPoint;
	private TreeViewer viewer;
	private LinkUtility linkUtility;
	private boolean from;
	private IMarker currentMarker;
	private IWorkbenchPage page;

	public LinkPopupDialog(Shell parent, IWorkbenchPage page, IMarker marker, Point textPoint, LinkUtility linkUtility, boolean from) {
		super(parent,
				PopupDialog.INFOPOPUPRESIZE_SHELLSTYLE,
				/*takeFocusOnOpen*/ false,
				/*persistSize*/ false,
				/*persistLocation*/ false,
				/*showDialogMenu*/ false,
				/*showPersistActions*/ true,
				"this is the title this is the title this is the title",
				"this is the info this is the info this is the info");
		this.textPoint = textPoint;
		this.linkUtility = linkUtility;
		this.from = from;
		this.currentMarker = marker;
		this.page = page;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.PopupDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
                | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        viewer.setContentProvider(new LinkContentProvider(linkUtility, from));

        viewer.getTree().setHeaderVisible(true);

        TreeViewerColumn resourceColumn = new TreeViewerColumn(viewer, SWT.NONE);
        TreeColumn column = resourceColumn.getColumn();
        column.setText("Resource");
        column.setWidth(300);
        resourceColumn.setLabelProvider(
        		new DelegatingStyledCellLabelProvider(
        				new LinkLabelProvider()));

        viewer.setInput(currentMarker);

        // http://www.vogella.com/tutorials/EclipseJFaceTree/article.html#selection-and-double-click-listener
        viewer.addDoubleClickListener(new IDoubleClickListener() {
            @Override
            public void doubleClick(DoubleClickEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                IMarker marker = (IMarker)selection.getFirstElement();
                try {
					IEditorPart part = IDE.openEditor(page, marker);
					part.toString();
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//                viewer.setExpandedState(first, !viewer.getExpandedState(first));
            }
        });

        final Tree tree = (Tree) viewer.getControl();
        tree.addSelectionListener(new SelectionAdapter() {
          @Override
          public void widgetSelected(SelectionEvent e) {
              TreeItem item = (TreeItem) e.item;
                if (item.getItemCount() > 0) {
                    item.setExpanded(!item.getExpanded());
                    // update the viewer
                    viewer.refresh();
                }
            }
        });

		return tree;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.PopupDialog#getDefaultLocation(org.eclipse.swt.graphics.Point)
	 */
	@Override
	protected Point getDefaultLocation(Point initialSize) {
		return textPoint;
	}
}
