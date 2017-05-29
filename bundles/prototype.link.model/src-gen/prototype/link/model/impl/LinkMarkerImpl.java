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
 *   <li>{@link prototype.link.model.impl.LinkMarkerImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link prototype.link.model.impl.LinkMarkerImpl#getLineNumber <em>Line Number</em>}</li>
 *   <li>{@link prototype.link.model.impl.LinkMarkerImpl#getCharStart <em>Char Start</em>}</li>
 *   <li>{@link prototype.link.model.impl.LinkMarkerImpl#getCharEnd <em>Char End</em>}</li>
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
	 * The default value of the '{@link #getMessage() <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMessage() <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessage()
	 * @generated
	 * @ordered
	 */
	protected String message = MESSAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLineNumber() <em>Line Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int LINE_NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLineNumber() <em>Line Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineNumber()
	 * @generated
	 * @ordered
	 */
	protected int lineNumber = LINE_NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getCharStart() <em>Char Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharStart()
	 * @generated
	 * @ordered
	 */
	protected static final int CHAR_START_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCharStart() <em>Char Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharStart()
	 * @generated
	 * @ordered
	 */
	protected int charStart = CHAR_START_EDEFAULT;

	/**
	 * The default value of the '{@link #getCharEnd() <em>Char End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharEnd()
	 * @generated
	 * @ordered
	 */
	protected static final int CHAR_END_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCharEnd() <em>Char End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharEnd()
	 * @generated
	 * @ordered
	 */
	protected int charEnd = CHAR_END_EDEFAULT;

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
	public String getMessage() {
		return message;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessage(String newMessage) {
		String oldMessage = message;
		message = newMessage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LinksPackage.LINK_MARKER__MESSAGE, oldMessage, message));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineNumber(int newLineNumber) {
		int oldLineNumber = lineNumber;
		lineNumber = newLineNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LinksPackage.LINK_MARKER__LINE_NUMBER, oldLineNumber, lineNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCharStart() {
		return charStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharStart(int newCharStart) {
		int oldCharStart = charStart;
		charStart = newCharStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LinksPackage.LINK_MARKER__CHAR_START, oldCharStart, charStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCharEnd() {
		return charEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharEnd(int newCharEnd) {
		int oldCharEnd = charEnd;
		charEnd = newCharEnd;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LinksPackage.LINK_MARKER__CHAR_END, oldCharEnd, charEnd));
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
			case LinksPackage.LINK_MARKER__MESSAGE:
				return getMessage();
			case LinksPackage.LINK_MARKER__LINE_NUMBER:
				return getLineNumber();
			case LinksPackage.LINK_MARKER__CHAR_START:
				return getCharStart();
			case LinksPackage.LINK_MARKER__CHAR_END:
				return getCharEnd();
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
			case LinksPackage.LINK_MARKER__MESSAGE:
				setMessage((String)newValue);
				return;
			case LinksPackage.LINK_MARKER__LINE_NUMBER:
				setLineNumber((Integer)newValue);
				return;
			case LinksPackage.LINK_MARKER__CHAR_START:
				setCharStart((Integer)newValue);
				return;
			case LinksPackage.LINK_MARKER__CHAR_END:
				setCharEnd((Integer)newValue);
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
			case LinksPackage.LINK_MARKER__MESSAGE:
				setMessage(MESSAGE_EDEFAULT);
				return;
			case LinksPackage.LINK_MARKER__LINE_NUMBER:
				setLineNumber(LINE_NUMBER_EDEFAULT);
				return;
			case LinksPackage.LINK_MARKER__CHAR_START:
				setCharStart(CHAR_START_EDEFAULT);
				return;
			case LinksPackage.LINK_MARKER__CHAR_END:
				setCharEnd(CHAR_END_EDEFAULT);
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
			case LinksPackage.LINK_MARKER__MESSAGE:
				return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
			case LinksPackage.LINK_MARKER__LINE_NUMBER:
				return lineNumber != LINE_NUMBER_EDEFAULT;
			case LinksPackage.LINK_MARKER__CHAR_START:
				return charStart != CHAR_START_EDEFAULT;
			case LinksPackage.LINK_MARKER__CHAR_END:
				return charEnd != CHAR_END_EDEFAULT;
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
		result.append(" (message: ");
		result.append(message);
		result.append(", lineNumber: ");
		result.append(lineNumber);
		result.append(", charStart: ");
		result.append(charStart);
		result.append(", charEnd: ");
		result.append(charEnd);
		result.append(')');
		return result.toString();
	}

} //LinkMarkerImpl
