package prototype.link.impl;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.osgi.service.component.annotations.Component;

import prototype.link.api.LinkController;


@Component(service=IContextFunction.class,
	property="service.context.key=prototype.link.api.LinkController")
public class LinkControllerContextFunction extends ContextFunction {

	/* (non-Javadoc)
	 * @see org.eclipse.e4.core.contexts.ContextFunction#compute(org.eclipse.e4.core.contexts.IEclipseContext, java.lang.String)
	 */
	@Override
	public Object compute(IEclipseContext context, String contextKey) {
		LinkController linkController = ContextInjectionFactory.make(LinkControllerImpl.class, context);
		MApplication app = context.get(MApplication.class);
		IEclipseContext appCtx = app.getContext();
		appCtx.set(LinkController.class, linkController);
		return linkController;

	}


}
