package us.coastalhacking.corvus.link.api;

public interface Link {

	/**
	 * Marker type. Type is required to be plug-in ID + marker type ID
	 */
	public String LINK_TYPE = "us.coastalhacking.corvus.link.api.linkMarker";
	
	public enum Direction {
		FROM,
		TO
	}
}
