package prototype.link.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IPath;
import org.junit.Before;
import org.junit.Test;

import prototype.link.api.Link;
import prototype.link.api.Link.Direction;
import prototype.link.model.LinkMarker;
import prototype.link.model.LinksFactory;

/*
 * This test peaks inside of a bunch of protected members making it
 * brittle to changes in the class under test
 */
public class AbstractLinkControllerImplTest extends AbstractLinkControllerImpl {

	@Before
	public void beforeTest() {
		root = LinksFactory.eINSTANCE.createWorkspaceRoot();
		atomicLastLinkMarker.set(null);
	}

	@Test
	public void shouldAddLink() throws Exception {

		long expectedMarkerId = 1;
		String expectedResourcePath = "a.txt";
		String expectedContainerPath = "/project";
		int charStart = 1;
		int charEnd = 3;
		int lineNo = 1;
		String message = "text";
		IMarker marker = createMarkerMock(expectedContainerPath, expectedResourcePath,
				expectedMarkerId, charStart, charEnd, lineNo, message);
		
		LinkMarker linkMarker = this.getOrCreateLinkMarkerNoLock(marker, /*create*/ false);
		assertNull(linkMarker);
		this.addLink(marker);
		
		linkMarker = this.getOrCreateLinkMarkerNoLock(marker, /*create*/ false);
		assertNotNull(linkMarker);
		assertEquals(expectedMarkerId, linkMarker.getId());
		assertEquals(charStart, linkMarker.getCharStart());
		assertEquals(charEnd, linkMarker.getCharEnd());
		assertEquals(lineNo, linkMarker.getLineNumber());
		assertEquals(message, linkMarker.getMessage());

	}

	/*
	 * Create a link, end it, then continue on it, and link to another 
	 */
	@Test
	public void shouldContinueLink() throws Exception {
		final long markerZeroId = 0L;
		IMarker markerZero = createMarkerMock("/project", "a.txt", markerZeroId, 5, 10, 1, "first");
		this.addLink(markerZero);
		this.endLink();

		final LinkMarker linkZero = this.getOrCreateLinkMarkerNoLock(markerZero, /*create*/ false);
		assertEquals(linkZero.getTo().size(), 0);

		this.continueLink(markerZero);

		final long markerOneId = 1L;
		IMarker markerOne = createMarkerMock("/project", "a.txt", markerOneId, 5, 10, 2, "second");
		this.addLink(markerOne);
		this.endLink();

		final LinkMarker linkOne = this.getOrCreateLinkMarkerNoLock(markerOne, /*create*/ false);
		assertEquals(linkZero.getTo().size(), 1);
		assertEquals(linkOne.getFrom().size(), 1);
		assertEquals(linkZero.getTo().get(0), linkOne);
		assertEquals(linkOne.getFrom().get(0), linkZero);


	}

	@Test
	public void shouldEndLink() throws Exception {
		IMarker markerZero = createMarkerMock("/project", "a.txt", 0, 5, 10, 1, "first");
		assertNull(atomicLastLinkMarker.get());
		this.addLink(markerZero);
		assertNotNull(atomicLastLinkMarker.get());
		this.endLink();
		assertNull(atomicLastLinkMarker.get());

	}

	@Test
	public void shouldRemoveMiddleLink() throws Exception {
		String containerPath = "/project";
		String resourcePath = "a.txt";
		IMarker markerZero = createMarkerMock(containerPath, resourcePath, 0, 5, 10, 1, "zero");
		IMarker markerOne = createMarkerMock(containerPath, resourcePath, 1, 15, 20, 2, "one");
		IMarker markerTwo = createMarkerMock(containerPath, resourcePath, 2, 25, 30, 3, "two");

		this.addLink(markerZero);
		this.addLink(markerOne);
		this.addLink(markerTwo);
		this.endLink();

		LinkMarker linkZero = this.getOrCreateLinkMarkerNoLock(markerZero, /*create*/ false);
		LinkMarker linkOne = this.getOrCreateLinkMarkerNoLock(markerOne, /*create*/ false);
		LinkMarker linkTwo = this.getOrCreateLinkMarkerNoLock(markerTwo, /*create*/ false);

		assertEquals(1, linkZero.getTo().size());
		assertEquals(linkOne, linkZero.getTo().get(0));
		assertEquals(1, linkTwo.getFrom().size());
		assertEquals(linkOne, linkTwo.getFrom().get(0));

		this.removeLink(markerOne);
		
		assertEquals(0, linkZero.getTo().size());
		assertEquals(0, linkTwo.getFrom().size());

	}
	
	@Test
	public void shouldRemoveSoloLink() throws Exception {
		String containerPath = "/project";
		String resourcePath = "a.txt";
		IMarker markerZero = createMarkerMock(containerPath, resourcePath, 0, 5, 10, 1, "zero");

		this.addLink(markerZero);
		this.endLink();

		LinkMarker linkZero = this.getOrCreateLinkMarkerNoLock(markerZero, /*create*/ false);

		assertNotNull(linkZero);

		this.removeLink(markerZero);

		linkZero = this.getOrCreateLinkMarkerNoLock(markerZero, /*create*/ false);
		assertNull(linkZero);

	}

	/*
	 * Needs a mocked up workspace
	 */
	@Test
	public void shouldGetMarkersToFrom() throws Exception {

		String containerPath = "/project";
		String resourcePath = "a.txt";
		IResource resource = createResource(containerPath, resourcePath);
		IMarker markerZero = createMarkerMock(resource, 0, 5, 10, 1, "zero");
		IMarker markerOne = createMarkerMock(resource, 1, 15, 20, 2, "one");

		this.workspace = mockWorkspace(resource);

		this.addLink(markerZero);
		this.addLink(markerOne);
		this.endLink();

		List<IMarker> zeroFromOne = new ArrayList<>();
		zeroFromOne.add(markerZero);
		assertEquals(zeroFromOne, this.getMarkers(markerOne, Direction.FROM));

		List<IMarker> oneToZero = new ArrayList<>();
		oneToZero.add(markerOne);
		assertEquals(oneToZero, this.getMarkers(markerZero, Direction.TO));
		
	}
	
	@Test
	public void shouldGetMarkerAtSelection() throws Exception {
		final int charStart = 5;
		final int charEnd = 10;
		final int lineNo = 1;
		IMarker expected = createMarkerMock("/project", "resource.txt", 0, charStart, charEnd, lineNo, "marker");
		IMarker[] markers = new IMarker[]{expected};
		IResource resource = expected.getResource();
		doReturn(markers).when(resource).findMarkers(eq(Link.LINK_TYPE), anyBoolean(), anyInt());

		IMarker actual = this.getMarkerAtSelection(resource, charStart + 1, charEnd + 10, lineNo);
		assertEquals(expected, actual);
	}
	
	/*
	 * Case when selection occurs before marker
	 */
	@Test
	public void shouldNotGetMarkerBeforeSelection() throws Exception {
		final int charStart = 5;
		final int charEnd = 10;
		final int lineNo = 1;
		IMarker expected = createMarkerMock("/project", "resource.txt", 0, charStart, charEnd, lineNo, "marker");
		IMarker[] markers = new IMarker[]{expected};
		IResource resource = expected.getResource();
		doReturn(markers).when(resource).findMarkers(eq(Link.LINK_TYPE), anyBoolean(), anyInt());

		IMarker actual = this.getMarkerAtSelection(resource, charStart - 3, charStart, lineNo);
		assertEquals(expected, actual);
	}
	
	/*
	 * Case when selection occurs after marker
	 */
	@Test
	public void shouldNotGetMarkerAfterSelection() throws Exception {
		final int charStart = 5;
		final int charEnd = 6;
		final int lineNo = 1;
		IMarker expected = createMarkerMock("/project", "resource.txt", 0, charStart, charEnd, lineNo, "marker");
		IMarker[] markers = new IMarker[]{expected};
		IResource resource = expected.getResource();
		doReturn(markers).when(resource).findMarkers(eq(Link.LINK_TYPE), anyBoolean(), anyInt());

		IMarker actual = this.getMarkerAtSelection(resource, charEnd, charEnd + 10, lineNo);
		assertEquals(expected, actual);
	}

	@Test
	public void shouldHaveMarkersToFrom() throws Exception {
		String containerPath = "/project";
		String resourcePath = "a.txt";
		IResource resource = createResource(containerPath, resourcePath);
		IMarker markerZero = createMarkerMock(resource, 0, 5, 10, 1, "zero");
		IMarker markerOne = createMarkerMock(resource, 1, 15, 20, 2, "one");

		this.workspace = mockWorkspace(resource);

		this.addLink(markerZero);
		this.addLink(markerOne);
		this.endLink();

		assertTrue(this.hasMarkers(markerZero, Direction.TO));
		assertTrue(this.hasMarkers(markerOne, Direction.FROM));

	}
	
	@Test
	public void shouldNotContinue() throws Exception {
		IMarker marker = createMarkerMock("/project", "resource.txt", 0, 5, 10, 2, "marker");
		this.addLink(marker);
		LinkMarker linkMarker = this.getOrCreateLinkMarkerNoLock(marker, /*create*/ false);
		assertTrue(linkMarker.getTo().isEmpty());
		assertTrue(linkMarker.getFrom().isEmpty());

		this.continueLink(marker);

		assertTrue(linkMarker.getTo().isEmpty());
		assertTrue(linkMarker.getFrom().isEmpty());
	}

	@Test
	public void shouldChange() throws Exception {
		IMarker marker = createMarkerMock("/project", "resource.txt", 0, -1, -1, -1, "");
		this.addLink(marker);

		final int expectedCharStart = 5;
		when(marker.getAttribute(IMarker.CHAR_START, -1)).thenReturn(expectedCharStart);
		this.modifyLink(marker);
		LinkMarker linkMarker = this.getOrCreateLinkMarkerNoLock(marker, /*create*/ false);
		assertEquals(expectedCharStart, linkMarker.getCharStart());
		
		final int expectedCharEnd = 10;
		when(marker.getAttribute(IMarker.CHAR_END, -1)).thenReturn(expectedCharEnd);
		this.modifyLink(marker);
		linkMarker = this.getOrCreateLinkMarkerNoLock(marker, /*create*/ false);
		assertEquals(expectedCharEnd, linkMarker.getCharEnd());
		
		final int expectedLineNo = 2;
		when(marker.getAttribute(IMarker.LINE_NUMBER, -1)).thenReturn(expectedLineNo);
		this.modifyLink(marker);
		linkMarker = this.getOrCreateLinkMarkerNoLock(marker, /*create*/ false);
		assertEquals(expectedLineNo, linkMarker.getLineNumber());
		
		final String message = "this is a message";
		when(marker.getAttribute(IMarker.MESSAGE, "")).thenReturn(message);
		this.modifyLink(marker);
		linkMarker = this.getOrCreateLinkMarkerNoLock(marker, /*create*/ false);
		assertEquals(message, linkMarker.getMessage());		

	}

	private IMarker createMarkerMock(String containerPath, String resourcePath, long markerId,
			int charStart, int charEnd, int lineNo, String message) throws Exception {

		IResource resource = createResource(containerPath, resourcePath);
		return createMarkerMock(resource, markerId, charStart, charEnd, lineNo, message);
	}

	private IMarker createMarkerMock(IResource resource, long markerId,
			int charStart, int charEnd, int lineNo, String message) throws Exception {

		IMarker marker = mock(IMarker.class);
		doReturn(Link.LINK_TYPE).when(marker).getType();

		when(resource.getMarker(markerId)).thenReturn(marker);

		when(marker.getResource()).thenReturn(resource);
		when(marker.getId()).thenReturn(markerId);
		when(marker.getAttribute(IMarker.CHAR_START, -1)).thenReturn(charStart);
		when(marker.getAttribute(IMarker.CHAR_END, -1)).thenReturn(charEnd);
		when(marker.getAttribute(IMarker.LINE_NUMBER, -1)).thenReturn(lineNo);
		when(marker.getAttribute(IMarker.MESSAGE, "")).thenReturn(message);

		return marker;
	}

	private IResource createResource(String containerPath, String resourcePath) throws Exception {
		IPath resourceIPath = mock(IPath.class);
		when(resourceIPath.toPortableString()).thenReturn(resourcePath);

		IResource resource = mock(IResource.class);
		when(resource.getProjectRelativePath()).thenReturn(resourceIPath);
		
		IContainer container = createContainer(containerPath);
		when(resource.getParent()).thenReturn(container);
		when(container.findMember(resourcePath)).thenReturn(resource);

		return resource;
	}
	
	private IContainer createContainer(String containerPath) {
		IPath path = mock(IPath.class);
		when(path.toPortableString()).thenReturn(containerPath);

		IContainer container = mock(IContainer.class);
		when(container.getFullPath()).thenReturn(path);

		return container;
	}

	private IWorkspace mockWorkspace(IResource resource) {

		IWorkspace workspace = mock(IWorkspace.class);
		IWorkspaceRoot root = mock(IWorkspaceRoot.class);
		when(workspace.getRoot()).thenReturn(root);

		IContainer container = resource.getParent();
		when(container.getAdapter(IContainer.class)).thenReturn(container);
		String containerPath = container.getFullPath().toPortableString();
		when(root.findMember(containerPath)).thenReturn(container);

		return workspace;
	}

	
}
