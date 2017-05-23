/**
 */
package prototype.link.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link prototype.link.model.Resource#getPortableProjectRelativePath <em>Portable Project Relative Path</em>}</li>
 *   <li>{@link prototype.link.model.Resource#getContainer <em>Container</em>}</li>
 *   <li>{@link prototype.link.model.Resource#getMarkers <em>Markers</em>}</li>
 * </ul>
 *
 * @see prototype.link.model.LinksPackage#getResource()
 * @model
 * @generated
 */
public interface Resource extends EObject {
	/**
	 * Returns the value of the '<em><b>Portable Project Relative Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Portable Project Relative Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Portable Project Relative Path</em>' attribute.
	 * @see #setPortableProjectRelativePath(String)
	 * @see prototype.link.model.LinksPackage#getResource_PortableProjectRelativePath()
	 * @model unique="false"
	 * @generated
	 */
	String getPortableProjectRelativePath();

	/**
	 * Sets the value of the '{@link prototype.link.model.Resource#getPortableProjectRelativePath <em>Portable Project Relative Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Portable Project Relative Path</em>' attribute.
	 * @see #getPortableProjectRelativePath()
	 * @generated
	 */
	void setPortableProjectRelativePath(String value);

	/**
	 * Returns the value of the '<em><b>Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link prototype.link.model.Container#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' container reference.
	 * @see #setContainer(Container)
	 * @see prototype.link.model.LinksPackage#getResource_Container()
	 * @see prototype.link.model.Container#getResources
	 * @model opposite="resources" transient="false"
	 * @generated
	 */
	Container getContainer();

	/**
	 * Sets the value of the '{@link prototype.link.model.Resource#getContainer <em>Container</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container</em>' container reference.
	 * @see #getContainer()
	 * @generated
	 */
	void setContainer(Container value);

	/**
	 * Returns the value of the '<em><b>Markers</b></em>' containment reference list.
	 * The list contents are of type {@link prototype.link.model.Marker}.
	 * It is bidirectional and its opposite is '{@link prototype.link.model.Marker#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Markers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Markers</em>' containment reference list.
	 * @see prototype.link.model.LinksPackage#getResource_Markers()
	 * @see prototype.link.model.Marker#getResource
	 * @model opposite="resource" containment="true"
	 * @generated
	 */
	EList<Marker> getMarkers();

} // Resource
