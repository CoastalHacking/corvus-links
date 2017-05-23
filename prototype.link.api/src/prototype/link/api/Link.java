package prototype.link.api;

public interface Link {

	/**
	 * Marker type. Type is required to be plug-in ID + marker type ID
	 */
	public String LINK_TYPE = "prototype.link.api.linkMarker";

	/**
	 * Used by command parameters
	 */
	public String FORWARDS = "forwards";

	/**
	 * Used by command parameters
	 */
	public String BACKWARDS = "backwards";
	
	public enum Direction {
		FROM,
		TO
	}
}
