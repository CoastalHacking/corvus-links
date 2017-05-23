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
import prototype.link.model.Marker;
import prototype.link.model.Resource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link prototype.link.model.impl.ResourceImpl#getPortableProjectRelativePath <em>Portable Project Relative Path</em>}</li>
 *   <li>{@link prototype.link.model.impl.ResourceImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link prototype.link.model.impl.ResourceImpl#getMarkers <em>Markers</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceImpl extends MinimalEObjectImpl.Container implements Resource {
	/**
	 * The default value of the '{@link #getPortableProjectRelativePath() <em>Portable Project Relative Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortableProjectRelativePath()
	 * @generated
	 * @ordered
	 */
	protected static final String PORTABLE_PROJECT_RELATIVE_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPortableProjectRelativePath() <em>Portable Project Relative Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortableProjectRelativePath()
	 * @generated
	 * @ordered
	 */
	protected String portableProjectRelativePath = PORTABLE_PROJECT_RELATIVE_PATH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMarkers() <em>Markers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarkers()
	 * @generated
	 * @ordered
	 */
	protected EList<Marker> markers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LinksPackage.Literals.RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPortableProjectRelativePath() {
		return portableProjectRelativePath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortableProjectRelativePath(String newPortableProjectRelativePath) {
		String oldPortableProjectRelativePath = portableProjectRelativePath;
		portableProjectRelativePath = newPortableProjectRelativePath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LinksPackage.RESOURCE__PORTABLE_PROJECT_RELATIVE_PATH, oldPortableProjectRelativePath, portableProjectRelativePath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public prototype.link.model.Container getContainer() {
		if (eContainerFeatureID() != LinksPackage.RESOURCE__CONTAINER) return null;
		return (prototype.link.model.Container)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public prototype.link.model.Container basicGetContainer() {
		if (eContainerFeatureID() != LinksPackage.RESOURCE__CONTAINER) return null;
		return (prototype.link.model.Container)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainer(prototype.link.model.Container newContainer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newContainer, LinksPackage.RESOURCE__CONTAINER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainer(prototype.link.model.Container newContainer) {
		if (newContainer != eInternalContainer() || (eContainerFeatureID() != LinksPackage.RESOURCE__CONTAINER && newContainer != null)) {
			if (EcoreUtil.isAncestor(this, newContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainer != null)
				msgs = ((InternalEObject)newContainer).eInverseAdd(this, LinksPackage.CONTAINER__RESOURCES, prototype.link.model.Container.class, msgs);
			msgs = basicSetContainer(newContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LinksPackage.RESOURCE__CONTAINER, newContainer, newContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Marker> getMarkers() {
		if (markers == null) {
			markers = new EObjectContainmentWithInverseEList<Marker>(Marker.class, this, LinksPackage.RESOURCE__MARKERS, LinksPackage.MARKER__RESOURCE);
		}
		return markers;
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
			case LinksPackage.RESOURCE__CONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContainer((prototype.link.model.Container)otherEnd, msgs);
			case LinksPackage.RESOURCE__MARKERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMarkers()).basicAdd(otherEnd, msgs);
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
			case LinksPackage.RESOURCE__CONTAINER:
				return basicSetContainer(null, msgs);
			case LinksPackage.RESOURCE__MARKERS:
				return ((InternalEList<?>)getMarkers()).basicRemove(otherEnd, msgs);
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
			case LinksPackage.RESOURCE__CONTAINER:
				return eInternalContainer().eInverseRemove(this, LinksPackage.CONTAINER__RESOURCES, prototype.link.model.Container.class, msgs);
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
			case LinksPackage.RESOURCE__PORTABLE_PROJECT_RELATIVE_PATH:
				return getPortableProjectRelativePath();
			case LinksPackage.RESOURCE__CONTAINER:
				if (resolve) return getContainer();
				return basicGetContainer();
			case LinksPackage.RESOURCE__MARKERS:
				return getMarkers();
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
			case LinksPackage.RESOURCE__PORTABLE_PROJECT_RELATIVE_PATH:
				setPortableProjectRelativePath((String)newValue);
				return;
			case LinksPackage.RESOURCE__CONTAINER:
				setContainer((prototype.link.model.Container)newValue);
				return;
			case LinksPackage.RESOURCE__MARKERS:
				getMarkers().clear();
				getMarkers().addAll((Collection<? extends Marker>)newValue);
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
			case LinksPackage.RESOURCE__PORTABLE_PROJECT_RELATIVE_PATH:
				setPortableProjectRelativePath(PORTABLE_PROJECT_RELATIVE_PATH_EDEFAULT);
				return;
			case LinksPackage.RESOURCE__CONTAINER:
				setContainer((prototype.link.model.Container)null);
				return;
			case LinksPackage.RESOURCE__MARKERS:
				getMarkers().clear();
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
			case LinksPackage.RESOURCE__PORTABLE_PROJECT_RELATIVE_PATH:
				return PORTABLE_PROJECT_RELATIVE_PATH_EDEFAULT == null ? portableProjectRelativePath != null : !PORTABLE_PROJECT_RELATIVE_PATH_EDEFAULT.equals(portableProjectRelativePath);
			case LinksPackage.RESOURCE__CONTAINER:
				return basicGetContainer() != null;
			case LinksPackage.RESOURCE__MARKERS:
				return markers != null && !markers.isEmpty();
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
		result.append(" (portableProjectRelativePath: ");
		result.append(portableProjectRelativePath);
		result.append(')');
		return result.toString();
	}

} //ResourceImpl
