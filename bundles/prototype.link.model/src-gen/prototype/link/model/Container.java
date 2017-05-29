/**
 */
package prototype.link.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link prototype.link.model.Container#getPortableFullPath <em>Portable Full Path</em>}</li>
 *   <li>{@link prototype.link.model.Container#getRoot <em>Root</em>}</li>
 *   <li>{@link prototype.link.model.Container#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @see prototype.link.model.LinksPackage#getContainer()
 * @model
 * @generated
 */
public interface Container extends EObject {
	/**
	 * Returns the value of the '<em><b>Portable Full Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Portable Full Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Portable Full Path</em>' attribute.
	 * @see #setPortableFullPath(String)
	 * @see prototype.link.model.LinksPackage#getContainer_PortableFullPath()
	 * @model unique="false"
	 * @generated
	 */
	String getPortableFullPath();

	/**
	 * Sets the value of the '{@link prototype.link.model.Container#getPortableFullPath <em>Portable Full Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Portable Full Path</em>' attribute.
	 * @see #getPortableFullPath()
	 * @generated
	 */
	void setPortableFullPath(String value);

	/**
	 * Returns the value of the '<em><b>Root</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link prototype.link.model.WorkspaceRoot#getContainers <em>Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' container reference.
	 * @see #setRoot(WorkspaceRoot)
	 * @see prototype.link.model.LinksPackage#getContainer_Root()
	 * @see prototype.link.model.WorkspaceRoot#getContainers
	 * @model opposite="containers" transient="false"
	 * @generated
	 */
	WorkspaceRoot getRoot();

	/**
	 * Sets the value of the '{@link prototype.link.model.Container#getRoot <em>Root</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' container reference.
	 * @see #getRoot()
	 * @generated
	 */
	void setRoot(WorkspaceRoot value);

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
	 * The list contents are of type {@link prototype.link.model.Resource}.
	 * It is bidirectional and its opposite is '{@link prototype.link.model.Resource#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resources</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference list.
	 * @see prototype.link.model.LinksPackage#getContainer_Resources()
	 * @see prototype.link.model.Resource#getContainer
	 * @model opposite="container" containment="true"
	 * @generated
	 */
	EList<Resource> getResources();

} // Container
