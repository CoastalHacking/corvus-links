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
 *   <li>{@link prototype.link.model.LinkMarker#getMessage <em>Message</em>}</li>
 *   <li>{@link prototype.link.model.LinkMarker#getLineNumber <em>Line Number</em>}</li>
 *   <li>{@link prototype.link.model.LinkMarker#getCharStart <em>Char Start</em>}</li>
 *   <li>{@link prototype.link.model.LinkMarker#getCharEnd <em>Char End</em>}</li>
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

	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see #setMessage(String)
	 * @see prototype.link.model.LinksPackage#getLinkMarker_Message()
	 * @model unique="false"
	 * @generated
	 */
	String getMessage();

	/**
	 * Sets the value of the '{@link prototype.link.model.LinkMarker#getMessage <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message</em>' attribute.
	 * @see #getMessage()
	 * @generated
	 */
	void setMessage(String value);

	/**
	 * Returns the value of the '<em><b>Line Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Number</em>' attribute.
	 * @see #setLineNumber(int)
	 * @see prototype.link.model.LinksPackage#getLinkMarker_LineNumber()
	 * @model unique="false"
	 * @generated
	 */
	int getLineNumber();

	/**
	 * Sets the value of the '{@link prototype.link.model.LinkMarker#getLineNumber <em>Line Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Number</em>' attribute.
	 * @see #getLineNumber()
	 * @generated
	 */
	void setLineNumber(int value);

	/**
	 * Returns the value of the '<em><b>Char Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Char Start</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Char Start</em>' attribute.
	 * @see #setCharStart(int)
	 * @see prototype.link.model.LinksPackage#getLinkMarker_CharStart()
	 * @model unique="false"
	 * @generated
	 */
	int getCharStart();

	/**
	 * Sets the value of the '{@link prototype.link.model.LinkMarker#getCharStart <em>Char Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Char Start</em>' attribute.
	 * @see #getCharStart()
	 * @generated
	 */
	void setCharStart(int value);

	/**
	 * Returns the value of the '<em><b>Char End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Char End</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Char End</em>' attribute.
	 * @see #setCharEnd(int)
	 * @see prototype.link.model.LinksPackage#getLinkMarker_CharEnd()
	 * @model unique="false"
	 * @generated
	 */
	int getCharEnd();

	/**
	 * Sets the value of the '{@link prototype.link.model.LinkMarker#getCharEnd <em>Char End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Char End</em>' attribute.
	 * @see #getCharEnd()
	 * @generated
	 */
	void setCharEnd(int value);

} // LinkMarker
