/**
 */
package prototype.link.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import prototype.link.model.LinkMarker;
import prototype.link.model.LinksPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link Marker</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link prototype.link.model.impl.LinkMarkerImpl#getTo <em>To</em>}</li>
 *   <li>{@link prototype.link.model.impl.LinkMarkerImpl#getFrom <em>From</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LinkMarkerImpl extends MarkerImpl implements LinkMarker {
	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected EList<LinkMarker> to;

	/**
	 * The cached value of the '{@link #getFrom() <em>From</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected EList<LinkMarker> from;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkMarkerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LinksPackage.Literals.LINK_MARKER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LinkMarker> getTo() {
		if (to == null) {
			to = new EObjectWithInverseResolvingEList.ManyInverse<LinkMarker>(LinkMarker.class, this, LinksPackage.LINK_MARKER__TO, LinksPackage.LINK_MARKER__FROM);
		}
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LinkMarker> getFrom() {
		if (from == null) {
			from = new EObjectWithInverseResolvingEList.ManyInverse<LinkMarker>(LinkMarker.class, this, LinksPackage.LINK_MARKER__FROM, LinksPackage.LINK_MARKER__TO);
		}
		return from;
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
			case LinksPackage.LINK_MARKER__TO:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTo()).basicAdd(otherEnd, msgs);
			case LinksPackage.LINK_MARKER__FROM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFrom()).basicAdd(otherEnd, msgs);
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
			case LinksPackage.LINK_MARKER__TO:
				return ((InternalEList<?>)getTo()).basicRemove(otherEnd, msgs);
			case LinksPackage.LINK_MARKER__FROM:
				return ((InternalEList<?>)getFrom()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LinksPackage.LINK_MARKER__TO:
				return getTo();
			case LinksPackage.LINK_MARKER__FROM:
				return getFrom();
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
			case LinksPackage.LINK_MARKER__TO:
				getTo().clear();
				getTo().addAll((Collection<? extends LinkMarker>)newValue);
				return;
			case LinksPackage.LINK_MARKER__FROM:
				getFrom().clear();
				getFrom().addAll((Collection<? extends LinkMarker>)newValue);
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
			case LinksPackage.LINK_MARKER__TO:
				getTo().clear();
				return;
			case LinksPackage.LINK_MARKER__FROM:
				getFrom().clear();
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
			case LinksPackage.LINK_MARKER__TO:
				return to != null && !to.isEmpty();
			case LinksPackage.LINK_MARKER__FROM:
				return from != null && !from.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //LinkMarkerImpl
