/**
 */
package prototype.link.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import prototype.link.model.LinksPackage;
import prototype.link.model.Resource;
import prototype.link.model.WorkspaceRoot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link prototype.link.model.impl.ContainerImpl#getPortableFullPath <em>Portable Full Path</em>}</li>
 *   <li>{@link prototype.link.model.impl.ContainerImpl#getRoot <em>Root</em>}</li>
 *   <li>{@link prototype.link.model.impl.ContainerImpl#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContainerImpl extends MinimalEObjectImpl.Container implements prototype.link.model.Container {
	/**
	 * The default value of the '{@link #getPortableFullPath() <em>Portable Full Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortableFullPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PORTABLE_FULL_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPortableFullPath() <em>Portable Full Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortableFullPath()
	 * @generated
	 * @ordered
	 */
	protected String portableFullPath = PORTABLE_FULL_PATH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getResources() <em>Resources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResources()
	 * @generated
	 * @ordered
	 */
	protected EList<Resource> resources;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LinksPackage.Literals.CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPortableFullPath() {
		return portableFullPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortableFullPath(String newPortableFullPath) {
		String oldPortableFullPath = portableFullPath;
		portableFullPath = newPortableFullPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LinksPackage.CONTAINER__PORTABLE_FULL_PATH, oldPortableFullPath, portableFullPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkspaceRoot getRoot() {
		if (eContainerFeatureID() != LinksPackage.CONTAINER__ROOT) return null;
		return (WorkspaceRoot)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkspaceRoot basicGetRoot() {
		if (eContainerFeatureID() != LinksPackage.CONTAINER__ROOT) return null;
		return (WorkspaceRoot)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoot(WorkspaceRoot newRoot, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRoot, LinksPackage.CONTAINER__ROOT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoot(WorkspaceRoot newRoot) {
		if (newRoot != eInternalContainer() || (eContainerFeatureID() != LinksPackage.CONTAINER__ROOT && newRoot != null)) {
			if (EcoreUtil.isAncestor(this, newRoot))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRoot != null)
				msgs = ((InternalEObject)newRoot).eInverseAdd(this, LinksPackage.WORKSPACE_ROOT__CONTAINERS, WorkspaceRoot.class, msgs);
			msgs = basicSetRoot(newRoot, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LinksPackage.CONTAINER__ROOT, newRoot, newRoot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Resource> getResources() {
		if (resources == null) {
			resources = new EObjectContainmentWithInverseEList<Resource>(Resource.class, this, LinksPackage.CONTAINER__RESOURCES, LinksPackage.RESOURCE__CONTAINER);
		}
		return resources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LinksPackage.CONTAINER__ROOT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRoot((WorkspaceRoot)otherEnd, msgs);
			case LinksPackage.CONTAINER__RESOURCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResources()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LinksPackage.CONTAINER__ROOT:
				return basicSetRoot(null, msgs);
			case LinksPackage.CONTAINER__RESOURCES:
				return ((InternalEList<?>)getResources()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case LinksPackage.CONTAINER__ROOT:
				return eInternalContainer().eInverseRemove(this, LinksPackage.WORKSPACE_ROOT__CONTAINERS, WorkspaceRoot.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LinksPackage.CONTAINER__PORTABLE_FULL_PATH:
				return getPortableFullPath();
			case LinksPackage.CONTAINER__ROOT:
				if (resolve) return getRoot();
				return basicGetRoot();
			case LinksPackage.CONTAINER__RESOURCES:
				return getResources();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case LinksPackage.CONTAINER__PORTABLE_FULL_PATH:
				setPortableFullPath((String)newValue);
				return;
			case LinksPackage.CONTAINER__ROOT:
				setRoot((WorkspaceRoot)newValue);
				return;
			case LinksPackage.CONTAINER__RESOURCES:
				getResources().clear();
				getResources().addAll((Collection<? extends Resource>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case LinksPackage.CONTAINER__PORTABLE_FULL_PATH:
				setPortableFullPath(PORTABLE_FULL_PATH_EDEFAULT);
				return;
			case LinksPackage.CONTAINER__ROOT:
				setRoot((WorkspaceRoot)null);
				return;
			case LinksPackage.CONTAINER__RESOURCES:
				getResources().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case LinksPackage.CONTAINER__PORTABLE_FULL_PATH:
				return PORTABLE_FULL_PATH_EDEFAULT == null ? portableFullPath != null : !PORTABLE_FULL_PATH_EDEFAULT.equals(portableFullPath);
			case LinksPackage.CONTAINER__ROOT:
				return basicGetRoot() != null;
			case LinksPackage.CONTAINER__RESOURCES:
				return resources != null && !resources.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (portableFullPath: ");
		result.append(portableFullPath);
		result.append(')');
		return result.toString();
	}

} //ContainerImpl
