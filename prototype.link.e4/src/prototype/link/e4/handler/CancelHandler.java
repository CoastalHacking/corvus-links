package prototype.link.e4.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;

import prototype.link.api.LinkController;

public class CancelHandler {

	@Inject LinkController linkController;
	
	@Execute
	public void execute() {
	}
}
