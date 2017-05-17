package prototype.link.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.eclipse.core.resources.IResource;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.log.Logger;
import org.junit.Test;

import prototype.link.api.Link;
import prototype.link.api.LinkDTO;
import prototype.link.api.LinkUtility;

public class LinkControllerTest {

	@Test
	public void testValidAdd() {

		IResource resource = mock(IResource.class);
		LinkDTO linkDTO = new LinkDTO();

		// Create and populate context
		IEclipseContext context = EclipseContextFactory.create();
		Set<LinkDTO> set = new CopyOnWriteArraySet<LinkDTO>();

		// Set 
		
		context.set(Logger.class, mock(Logger.class));
		LinkUtility linkUtility = ContextInjectionFactory.make(LinkUtilityImpl.class, context);
		context.set(LinkUtility.class, linkUtility);

 		set = (Set<LinkDTO>)context.get(Link.CONTEXT_KEY);
 		assertEquals(0, set.size());

		LinkControllerImpl linkController = ContextInjectionFactory.make(LinkControllerImpl.class, context);
		assertNotNull(linkController);


		doReturn(true).when(resource).exists();
		linkDTO.resource = resource;
 		linkController.add(linkDTO);
 		
 		set = (Set<LinkDTO>)context.get(Link.CONTEXT_KEY);
 		assertEquals(1, set.size());
	}

}
