package prototype.link.ui.handler;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;

import prototype.link.api.LinkDTO;
import prototype.link.api.LinkDTOFactory;
import prototype.link.api.LinkDTOSet;

@SuppressWarnings("restriction")
public class StartHandler {

	@Inject LinkDTOFactory dtoFactory;

	@Inject LinkDTOSet linkSet;

	@Inject Logger logger;

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) ITextSelection textSelection,
			IEditorPart editorPart) {

		LinkDTO dto = dtoFactory.create(editorPart, textSelection);
		logger.debug("Starting and adding: " + dto);
		linkSet.start(dto);

	}
}
