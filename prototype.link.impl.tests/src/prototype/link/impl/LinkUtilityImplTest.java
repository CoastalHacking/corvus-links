package prototype.link.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import prototype.link.api.Link;

@RunWith(MockitoJUnitRunner.class)
public class LinkUtilityImplTest extends LinkUtilityImpl {

	@Test
	public void testParseAttribute() {
		final String payload = "1,2,3";
		final List<Long> expected = new ArrayList<>();
		expected.add(1L);
		expected.add(2L);
		expected.add(3L);
		assertArrayEquals(expected.toArray(), this.parseAttribute(payload).toArray());
	}
	
	@Test
	public void testUpdateFrom() throws Exception {

		IMarker marker = mock(IMarker.class);

		doReturn(Link.LINK_TYPE).when(marker).getType();

		doReturn("1,2,3").when(marker).getAttribute(Link.LINK_FROM, "");

		this.updateFrom(marker, 4L);

		verify(marker, atLeastOnce()).setAttribute(Link.LINK_FROM, "1,2,3,4");

	}

	@Test
	public void testUpdateTo() throws Exception {

		IMarker marker = mock(IMarker.class);

		doReturn(Link.LINK_TYPE).when(marker).getType();

		doReturn("1,2,3").when(marker).getAttribute(Link.LINK_TO, "");

		this.updateTo(marker, 4L);

		verify(marker, atLeastOnce()).setAttribute(Link.LINK_TO, "1,2,3,4");
	}

	@Test
	public void testGetFrom() throws Exception {

		IMarker marker = mock(IMarker.class);
		List<Long> expected = new ArrayList<>();
		expected.add(1L);
		expected.add(2L);
		expected.add(3L);

		doReturn(Link.LINK_TYPE).when(marker).getType();

		doReturn("1,2,3").when(marker).getAttribute(Link.LINK_FROM, "");

		List<Long> actual = this.getFrom(marker);

		verify(marker, atLeastOnce()).getAttribute(Link.LINK_FROM, "");
		assertArrayEquals(expected.toArray(), actual.toArray());
	}

	@Test
	public void testGetTo() throws Exception {
		IMarker marker = mock(IMarker.class);
		List<Long> expected = new ArrayList<>();
		expected.add(1L);
		expected.add(2L);
		expected.add(3L);

		doReturn(Link.LINK_TYPE).when(marker).getType();

		doReturn("1,2,3").when(marker).getAttribute(Link.LINK_TO, "");

		List<Long> actual = this.getTo(marker);

		verify(marker, atLeastOnce()).getAttribute(Link.LINK_TO, "");
		assertArrayEquals(expected.toArray(), actual.toArray());
	}

}
