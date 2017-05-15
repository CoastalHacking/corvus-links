package prototype.link.impl;

import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.e4.core.services.log.Logger;

import prototype.link.api.Link;
import prototype.link.api.LinkController;
import prototype.link.api.LinkDTO;
import prototype.link.api.LinkUtility;

@SuppressWarnings("restriction")
public class LinkControllerImpl implements LinkController {

	@Inject Logger logger;
	
	@Inject LinkUtility linkUtility;

	private enum State {
		INITIALIZED,
		ADD,
		END
	}

	private final Object lock = new Object();
	
	// singleton state values
	private LinkedList<LinkDTO> linkDTOs;
	private LinkedList<LinkDTO> undoLinkDTOs;
	private State state;
	private State undoState;
	private IWorkspace workspace;

	@Override
	public void popup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel() {
		synchronized (lock) {
			initialize();
		}
	}

	@Override
	public void start(LinkDTO linkDTO) {
		if (!validLinkDTO(linkDTO)) {
			logger.warn("Invalid DTO: " + linkDTO);
			return;
		}

		assert (state == State.INITIALIZED);
		synchronized (lock) {
			workspace = linkDTO.iResource.getWorkspace();
			stashAndTransition(State.ADD);
			linkDTOs.add(linkDTO);
		}
	}

	@Override
	public void add(LinkDTO linkDTO) {
		if (!validLinkDTO(linkDTO)) {
			logger.warn("Invalid DTO: " + linkDTO);
			return;
		}

		assert (state == State.ADD);
		synchronized (lock) {
			stash();
			linkDTOs.add(linkDTO);
		}
	}


	@Override
	public synchronized void end(LinkDTO linkDTO) {

		// use validation and assertion in callee
		// run outside of lock
		add(linkDTO);

		LinkedList<LinkDTO> toAdd = new LinkedList<LinkDTO>();
		synchronized (lock) {
			stashAndTransition(State.END);
			toAdd.addAll(linkDTOs);

			// clear out before adding, to exit the lock
			initialize();
		}
		// run outside of the lock
		// method is also synchronized to avoid a race on calling end quickly
		addOrUpdateMarkers(toAdd);
	}

	@Override
	public void undo() {
		synchronized (lock) {
			logger.debug(String.format("Undoing state from %s to %s", state, undoState));
			state = undoState;
			linkDTOs = new LinkedList<LinkDTO>(undoLinkDTOs);
		}
	}

	@PostConstruct
	public void construct() {
		synchronized (lock) {
			initialize();
		}
	}
	
	private boolean validLinkDTO(LinkDTO dto) {
		boolean result = false;
		result = dto != null && dto.iResource != null && dto.iResource.exists();
		if (result && workspace != null) {
			// all resources need to be in the same workspace
			// workspace is lazily set after first add
			result = dto.iResource.getWorkspace().equals(workspace);
		}

		return result; 
	}

	/*
	 * Four methods below need to be locked by callers
	 */
	private void initialize() {
		stashAndTransition(State.INITIALIZED);
		linkDTOs = new LinkedList<>();
		workspace = null;
	}
	private void stashAndTransition(State next) {
		stash();
		transition(next);
	}
	private void stash() {
		undoLinkDTOs = new LinkedList<LinkDTO>(linkDTOs);
	}
	private void transition(State next) {

		logger.debug(String.format("Transitioning link undo state from %s to %s", undoState, state));
		undoState = state;

		logger.debug(String.format("Transitioning link state from %s to %s", state, next));		
		state = next;
	}

	private void addOrUpdateMarkers(LinkedList<LinkDTO> toAdd) {

		for (int i=0; i < toAdd.size(); i++) {
			final LinkDTO l = toAdd.get(i);
			final IResource resource = l.iResource;

			if (validResource(resource)) {
				IMarker marker = markerAtLocation(resource,
						l.iTextSelectionDTO.offset,
						l.iTextSelectionDTO.offset + l.iTextSelectionDTO.length);

				if (marker == null) {
					try {
						// can't do this, need to encapsulate in runnable
						marker = resource.createMarker(Link.LINK_TYPE);
					} catch (CoreException e) {
						logger.warn(e);
					}
				}

				// update (or initial add)
				/*
				 * 	* if no marker, create marker; else update
		LINK_TO
		LINK_FROM
		LOCATION
	* add resource to MultiRule
				 */
				if (marker != null) {
				}
			}
		}
	}

	private ISchedulingRule createRule(LinkedList<LinkDTO> linkDTOs) {
		ISchedulingRule[] nestedRules = linkDTOs.stream().map(l -> l.iResource).toArray(ISchedulingRule[]::new); 
		return new MultiRule(nestedRules);
	}
	
	/*
	 * Marker-related methods
	 */
	private void scheduleMarkers(LinkedList<LinkDTO> linkDTOs) {
		workspace.run(action,
				createRule(linkDTOs),
				IWorkspace.AVOID_UPDATE,
				new NullProgressMonitor());
	}
	
	
	private IMarker markerAtLocation(IResource resource, int charStart, int charEnd) {
		IMarker marker = null;

		try {
			for (IMarker m: resource.findMarkers(Link.LINK_TYPE, /*includeSubtypes*/true, /*?*/ IResource.DEPTH_ONE)) {
				final int markerCharStart = safeInteger((String)m.getAttribute(IMarker.CHAR_START, "-1"));
				final int markerCharEnd = safeInteger((String)m.getAttribute(IMarker.CHAR_END, "-1"));

				// if the start character is after this end character, skip
				if (markerCharStart > charEnd)
					continue;
				
				// if the end character is before this start character, skip
				if (markerCharEnd < charStart)
					continue;

				// keep lowest resource-relative ID
				if (marker == null || marker.getId() > m.getId())
					marker = m;
			}
		} catch (CoreException e) {
			logger.warn(e);
		}
		return marker;
	}
	private boolean validResource(IResource resource) {
		return resource.exists(); // is project open and resource exist in workspace?
	}
	private int safeInteger(String character) {
		int result = -1;
		try {
			Integer.parseInt(character);
		} catch (NumberFormatException e) {
			logger.warn("Invalid character for expected integer value: " + character);
		}
		return result;
	}
}
