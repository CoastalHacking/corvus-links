package prototype.link.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;

import prototype.link.api.LinkContext;

public class EndHandler {

	@Inject LinkContext linkContext;

	@Execute
	public void execute() {
		linkContext.marker = null;
	}
}
