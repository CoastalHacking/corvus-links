package us.coastalhacking.corvus.link.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;

import us.coastalhacking.corvus.link.api.LinkController;

public class EndHandler {

	@Inject LinkController linkController;

	@Execute
	public void execute() {
		linkController.endLink();
	}
}
