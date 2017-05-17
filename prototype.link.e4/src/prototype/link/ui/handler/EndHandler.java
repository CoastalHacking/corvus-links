package prototype.link.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;

import prototype.link.api.LinkMarkerDTO;

public class EndHandler {

	@Inject LinkMarkerDTO markerDTO;

	@Execute
	public void execute() {
		markerDTO.marker = null;
	}
}
