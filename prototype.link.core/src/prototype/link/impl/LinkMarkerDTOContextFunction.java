package prototype.link.impl;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.osgi.service.component.annotations.Component;

import prototype.link.api.LinkMarkerDTO;


/*
 * This class exists because there's a race when addons and handlers are added
 * Don't know how to ensure the addon wins the race, so as a work-round
 * using a context function, which seems to load prior to the handlers being called 
 */
@Component(service=IContextFunction.class,
	property="service.context.key=prototype.link.api.LinkMarkerDTO")
public class LinkMarkerDTOContextFunction extends ContextFunction {

	/* (non-Javadoc)
	 * @see org.eclipse.e4.core.contexts.ContextFunction#compute(org.eclipse.e4.core.contexts.IEclipseContext, java.lang.String)
	 */
	@Override
	public Object compute(IEclipseContext context, String contextKey) {
		LinkMarkerDTO dto = new LinkMarkerDTO();
		MApplication app = context.get(MApplication.class);
		IEclipseContext appCtx = app.getContext();
		appCtx.set(LinkMarkerDTO.class, dto);
		appCtx.declareModifiable(LinkMarkerDTO.class);
		return dto;
	}


}
