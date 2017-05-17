package prototype.link.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.log.Logger;

import prototype.link.api.LinkDTOSet;

@SuppressWarnings("restriction")
public class CancelHandler {
	
	@Inject LinkDTOSet linkSet;

	@Inject Logger logger;

	@Execute
	public void execute() {

		logger.debug("Clearing LinkDTOSet");
		linkSet.clear();

	}
}
