package prototype.link.api;

import java.util.Collection;
import java.util.HashSet;

public class LinkDTOSet extends HashSet<LinkDTO> {

	
	private static final long serialVersionUID = -9162555560242850631L;

	public enum State {
		START,
		ADD,
		END
	}

	State state;

	public LinkDTOSet() {
		super();
		state = State.START;
	}

	public LinkDTOSet(Collection<? extends LinkDTO> linkDTOs) {
		super(linkDTOs);
		state = State.ADD; // DTOs already added
	}

	@Override
	public boolean add(LinkDTO linkDTO) {
		checkState(State.ADD);
		return super.add(linkDTO);
	}

	/* (non-Javadoc)
	 * @see java.util.concurrent.CopyOnWriteArraySet#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends LinkDTO> c) {
		checkState(State.ADD);
		return super.addAll(c);
	}

	/* (non-Javadoc)
	 * @see java.util.HashSet#clear()
	 */
	@Override
	public void clear() {
		super.clear();
		state = State.START;
	}

	public boolean start(LinkDTO linkDTO) {
		clear();
		state = State.ADD;
		return add(linkDTO);
	}

	public boolean end(LinkDTO linkDTO) {
		final boolean result = add(linkDTO);
		state = State.END;
		return result;
	}

	public void checkState(State expected) {
		if (state != expected) {
			throw new IllegalStateException(
					String.format("Current state: '%s'; required state: '%s'", state, expected));
		}
	}

}
