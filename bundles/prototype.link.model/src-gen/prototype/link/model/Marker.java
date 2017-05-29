/**
 */
package prototype.link.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Marker</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link prototype.link.model.Marker#getId <em>Id</em>}</li>
 *   <li>{@link prototype.link.model.Marker#getResource <em>Resource</em>}</li>
 * </ul>
 *
 * @see prototype.link.model.LinksPackage#getMarker()
 * @model
 * @generated
 */
public interface Marker extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(long)
	 * @see prototype.link.model.LinksPackage#getMarker_Id()
	 * @model unique="false"
	 * @generated
	 */
	long getId();

	/**
	 * Sets the value of the '{@link prototype.link.model.Marker#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(long value);

	/**
	 * Returns the value of the '<em><b>Resource</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link prototype.link.model.Resource#getMarkers <em>Markers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource</em>' container reference.
	 * @see #setResource(Resource)
	 * @see prototype.link.model.LinksPackage#getMarker_Resource()
	 * @see prototype.link.model.Resource#getMarkers
	 * @model opposite="markers" transient="false"
	 * @generated
	 */
	Resource getResource();

	/**
	 * Sets the value of the '{@link prototype.link.model.Marker#getResource <em>Resource</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource</em>' container reference.
	 * @see #getResource()
	 * @generated
	 */
	void setResource(Resource value);

} // Marker
