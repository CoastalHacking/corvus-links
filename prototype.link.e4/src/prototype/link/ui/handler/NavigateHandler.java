package prototype.link.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;

import prototype.link.api.LinkController;

public class NavigateHandler {

	@Inject LinkController linkController;

	@Execute
	public void execute() {
	}
}
