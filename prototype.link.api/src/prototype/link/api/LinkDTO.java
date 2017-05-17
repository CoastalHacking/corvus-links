package prototype.link.api;

import org.eclipse.core.resources.IResource;

public class LinkDTO {

	public ITextSelectionDTO textSelectionDTO;
	
	public IResource resource;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName());
		builder.append("[");
		if (resource != null && resource.exists()) {
			builder.append(String.format("Resource=%s;", resource.getName()));
		}
		if (textSelectionDTO != null) {
			builder.append(
				String.format("TextSelection(endline=%d, length=%d, offset=%d, startLine=%d, text=%s)",
						textSelectionDTO.endLine,
						textSelectionDTO.length,
						textSelectionDTO.offset,
						textSelectionDTO.startLine,
						textSelectionDTO.text));
		}
		builder.append("]");
		return super.toString();
	}

}
