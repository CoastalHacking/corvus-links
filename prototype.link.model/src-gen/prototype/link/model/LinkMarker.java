/**
 */
package prototype.link.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link Marker</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link prototype.link.model.LinkMarker#getTo <em>To</em>}</li>
 *   <li>{@link prototype.link.model.LinkMarker#getFrom <em>From</em>}</li>
 * </ul>
 *
 * @see prototype.link.model.LinksPackage#getLinkMarker()
 * @model
 * @generated
 */
public interface LinkMarker extends Marker {
	/**
	 * Returns the value of the '<em><b>To</b></em>' reference list.
	 * The list contents are of type {@link prototype.link.model.LinkMarker}.
	 * It is bidirectional and its opposite is '{@link prototype.link.model.LinkMarker#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference list.
	 * @see prototype.link.model.LinksPackage#getLinkMarker_To()
	 * @see prototype.link.model.LinkMarker#getFrom
	 * @model opposite="from"
	 * @generated
	 */
	EList<LinkMarker> getTo();

	/**
	 * Returns the value of the '<em><b>From</b></em>' reference list.
	 * The list contents are of type {@link prototype.link.model.LinkMarker}.
	 * It is bidirectional and its opposite is '{@link prototype.link.model.LinkMarker#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference list.
	 * @see prototype.link.model.LinksPackage#getLinkMarker_From()
	 * @see prototype.link.model.LinkMarker#getTo
	 * @model opposite="to"
	 * @generated
	 */
	EList<LinkMarker> getFrom();

} // LinkMarker
