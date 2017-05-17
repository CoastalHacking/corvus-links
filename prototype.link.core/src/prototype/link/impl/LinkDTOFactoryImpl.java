package prototype.link.impl;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;

import prototype.link.api.ITextSelectionDTO;
import prototype.link.api.LinkDTO;
import prototype.link.api.LinkDTOFactory;

public class LinkDTOFactoryImpl implements LinkDTOFactory {

	/*
	 * (non-Javadoc)
	 * @see prototype.link.api.LinkDTOFactory#create(org.eclipse.core.resources.IResource, org.eclipse.jface.text.ITextSelection)
	 */
	@Override
	public LinkDTO create(IEditorPart editorPart, ITextSelection textSelection) {

		final IResource resource = Adapters.adapt(editorPart.getEditorInput(), IResource.class);

		LinkDTO dto = new LinkDTO();
		dto.resource = resource;

		final ITextSelectionDTO selectionDTO = new ITextSelectionDTO();
		selectionDTO.endLine = textSelection.getEndLine();
		selectionDTO.length = textSelection.getLength();
		selectionDTO.offset = textSelection.getOffset();
		selectionDTO.startLine = textSelection.getStartLine();
		selectionDTO.text = textSelection.getText();
		dto.textSelectionDTO = selectionDTO;

		return dto;
	}

}
