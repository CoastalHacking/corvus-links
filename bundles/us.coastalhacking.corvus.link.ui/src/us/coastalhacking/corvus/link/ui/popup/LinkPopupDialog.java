package us.coastalhacking.corvus.link.ui.popup;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

import us.coastalhacking.corvus.link.api.LinkController;
import us.coastalhacking.corvus.link.api.Link.Direction;

public class LinkPopupDialog extends PopupDialog {

	private Point textPoint;
	private TreeViewer viewer;
	private LinkController linkController;
	private IMarker currentMarker;
	private IWorkbenchPage page;
	private Direction direction;

	public LinkPopupDialog(Shell parent, IWorkbenchPage page, IMarker marker, Point textPoint, LinkController linkController, Direction direction) {
		super(parent,
				PopupDialog.INFOPOPUPRESIZE_SHELLSTYLE,
				/*takeFocusOnOpen*/ true,
				/*persistSize*/ false,
				/*persistLocation*/ false,
				/*showDialogMenu*/ false,
				/*showPersistActions*/ true,
				/*title*/ "Navigate " + (direction == Direction.FROM ? "from" : "to"),
				/*info*/ null);
		this.textPoint = textPoint;
		this.linkController = linkController;
//		this.from = from;
		this.direction = direction;
		this.currentMarker = marker;
		this.page = page;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.PopupDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite dialogParent) {
		
		Composite composite = (Composite) super.createDialogArea(dialogParent);

		viewer = new TreeViewer(composite, SWT.MULTI | SWT.H_SCROLL
                | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

//        viewer.setContentProvider(new LinkContentProvider(linkUtility, from));
		viewer.setContentProvider(new LinkContentProvider(linkController, direction));

        final Tree tree = viewer.getTree();
        tree.setHeaderVisible(true);

        // Set label providers
        TreeViewerColumn resourceViewerColumn = new TreeViewerColumn(viewer, SWT.NONE);
        TreeColumn resourceColumn = resourceViewerColumn.getColumn();
        resourceColumn.setText("Resource");
        resourceColumn.setWidth(100);
        resourceViewerColumn.setLabelProvider(
        		new DelegatingStyledCellLabelProvider(
        				new LinkLabelProvider.LinkResourceLabelProvider()));
        
        TreeViewerColumn locationViewerColumn = new TreeViewerColumn(viewer, SWT.NONE);
        TreeColumn locationColumn = locationViewerColumn.getColumn();
        locationColumn.setText("Location");
        locationColumn.setWidth(100);
        locationViewerColumn.setLabelProvider(
        		new DelegatingStyledCellLabelProvider(
        				new LinkLabelProvider.LinkLocationLabelProvider()));
        
        TreeViewerColumn selectionViewerColumn = new TreeViewerColumn(viewer, SWT.NONE);
        TreeColumn selectionColumn = selectionViewerColumn.getColumn();
        selectionColumn.setText("Selection");
        selectionColumn.setWidth(100);
        selectionViewerColumn.setLabelProvider(
        		new DelegatingStyledCellLabelProvider(
        				new LinkLabelProvider.LinkSelectionLabelProvider()));

        viewer.setInput(currentMarker);

        // http://www.vogella.com/tutorials/EclipseJFaceTree/article.html#selection-and-double-click-listener
        viewer.addDoubleClickListener(new IDoubleClickListener() {
            @Override
            public void doubleClick(DoubleClickEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                IMarker marker = (IMarker)selection.getFirstElement();
                try {
                	LinkPopupDialog.this.close();
					IDE.openEditor(page, marker);

				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        // http://www.vogella.com/tutorials/EclipseJFaceTree/article.html#adjusting-tree-columns-treecolumns-on-expand
        tree.addListener(SWT.Expand, new Listener() {

			@Override
			public void handleEvent(Event event) {
				TreeItem treeItem = (TreeItem)event.item;
				final TreeColumn[] treeColumns = treeItem.getParent().getColumns();
				tree.getDisplay().asyncExec(new Runnable() {

					 @Override
					 public void run() {
					    for (TreeColumn treeColumn : treeColumns)
					         treeColumn.pack();
					 }
				});
			}
		});

        GridDataFactory.fillDefaults().grab(true, true).applyTo(tree);
        return composite;

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.PopupDialog#getDefaultLocation(org.eclipse.swt.graphics.Point)
	 */
	@Override
	protected Point getDefaultLocation(Point initialSize) {
		return textPoint;
	}

}
