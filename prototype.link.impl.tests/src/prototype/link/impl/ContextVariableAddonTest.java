package prototype.link.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.resources.IResource;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.log.Logger;
import org.junit.Test;

import prototype.link.api.Link;
import prototype.link.api.LinkDTO;
import prototype.link.api.LinkUtility;

public class ContextVariableAddonTest {

	public static class InjectConsumer { @Inject public Set<LinkDTO> set; }
	public static class InjectNamedConsumer { @Inject @Named(Link.CONTEXT_KEY) public Set<LinkDTO> set; }

	@Test
	public void testUnnamed() {

		// Create context
		IEclipseContext context = EclipseContextFactory.create();
		Set<LinkDTO> set = new CopyOnWriteArraySet<LinkDTO>();
		context.set(Set.class, set);

		InjectConsumer injectConsumer = ContextInjectionFactory.make(InjectConsumer.class, context);
		assertNotNull(injectConsumer.set);

	}

	@Test
	public void testNamed() {

		// Create context
		IEclipseContext context = EclipseContextFactory.create();
		Set<LinkDTO> set = new CopyOnWriteArraySet<LinkDTO>();
		context.set(Link.CONTEXT_KEY, set);

		InjectNamedConsumer injectNamedConsumer = ContextInjectionFactory.make(InjectNamedConsumer.class, context);
		assertNotNull(injectNamedConsumer.set);
	}

}
