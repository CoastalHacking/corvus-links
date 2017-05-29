/**
 */
package prototype.link.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workspace Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link prototype.link.model.WorkspaceRoot#getContainers <em>Containers</em>}</li>
 * </ul>
 *
 * @see prototype.link.model.LinksPackage#getWorkspaceRoot()
 * @model
 * @generated
 */
public interface WorkspaceRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Containers</b></em>' containment reference list.
	 * The list contents are of type {@link prototype.link.model.Container}.
	 * It is bidirectional and its opposite is '{@link prototype.link.model.Container#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containers</em>' containment reference list.
	 * @see prototype.link.model.LinksPackage#getWorkspaceRoot_Containers()
	 * @see prototype.link.model.Container#getRoot
	 * @model opposite="root" containment="true"
	 * @generated
	 */
	EList<Container> getContainers();

} // WorkspaceRoot
