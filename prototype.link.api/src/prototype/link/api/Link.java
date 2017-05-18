package prototype.link.api;

public interface Link {

	/**
	 * Marker type. Type is plug-in ID + marker type ID
	 */
	public String LINK_TYPE = "prototype.link.api.linkMarker";

	public String LINK_TO = "link_to";

	public String LINK_FROM = "link_from";
	
	public String FORWARDS = "forwards";
	
	public String BACKWARDS = "backwards";
	
	public String CONTEXT_KEY = "prototype.link.context.key";
	
	public enum Direction {
		FROM,
		TO
	}
}
