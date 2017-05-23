/**
 */
package prototype.link.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see prototype.link.model.LinksFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel fileExtensions='links' prefix='Links' basePackage='prototype.link'"
 * @generated
 */
public interface LinksPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "https://coastalhacking.us/corvus/links";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "prototype.link.model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LinksPackage eINSTANCE = prototype.link.model.impl.LinksPackageImpl.init();

	/**
	 * The meta object id for the '{@link prototype.link.model.impl.WorkspaceRootImpl <em>Workspace Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see prototype.link.model.impl.WorkspaceRootImpl
	 * @see prototype.link.model.impl.LinksPackageImpl#getWorkspaceRoot()
	 * @generated
	 */
	int WORKSPACE_ROOT = 0;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_ROOT__CONTAINERS = 0;

	/**
	 * The number of structural features of the '<em>Workspace Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_ROOT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Workspace Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_ROOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link prototype.link.model.impl.ContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see prototype.link.model.impl.ContainerImpl
	 * @see prototype.link.model.impl.LinksPackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 1;

	/**
	 * The feature id for the '<em><b>Portable Full Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__PORTABLE_FULL_PATH = 0;

	/**
	 * The feature id for the '<em><b>Root</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ROOT = 1;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__RESOURCES = 2;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link prototype.link.model.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see prototype.link.model.impl.ResourceImpl
	 * @see prototype.link.model.impl.LinksPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 2;

	/**
	 * The feature id for the '<em><b>Portable Project Relative Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__PORTABLE_PROJECT_RELATIVE_PATH = 0;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__CONTAINER = 1;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__MARKERS = 2;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link prototype.link.model.impl.MarkerImpl <em>Marker</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see prototype.link.model.impl.MarkerImpl
	 * @see prototype.link.model.impl.LinksPackageImpl#getMarker()
	 * @generated
	 */
	int MARKER = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER__ID = 0;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER__RESOURCE = 1;

	/**
	 * The number of structural features of the '<em>Marker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Marker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link prototype.link.model.impl.LinkMarkerImpl <em>Link Marker</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see prototype.link.model.impl.LinkMarkerImpl
	 * @see prototype.link.model.impl.LinksPackageImpl#getLinkMarker()
	 * @generated
	 */
	int LINK_MARKER = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_MARKER__ID = MARKER__ID;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_MARKER__RESOURCE = MARKER__RESOURCE;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_MARKER__TO = MARKER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_MARKER__FROM = MARKER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Link Marker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_MARKER_FEATURE_COUNT = MARKER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Link Marker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_MARKER_OPERATION_COUNT = MARKER_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link prototype.link.model.WorkspaceRoot <em>Workspace Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workspace Root</em>'.
	 * @see prototype.link.model.WorkspaceRoot
	 * @generated
	 */
	EClass getWorkspaceRoot();

	/**
	 * Returns the meta object for the containment reference list '{@link prototype.link.model.WorkspaceRoot#getContainers <em>Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Containers</em>'.
	 * @see prototype.link.model.WorkspaceRoot#getContainers()
	 * @see #getWorkspaceRoot()
	 * @generated
	 */
	EReference getWorkspaceRoot_Containers();

	/**
	 * Returns the meta object for class '{@link prototype.link.model.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see prototype.link.model.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for the attribute '{@link prototype.link.model.Container#getPortableFullPath <em>Portable Full Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Portable Full Path</em>'.
	 * @see prototype.link.model.Container#getPortableFullPath()
	 * @see #getContainer()
	 * @generated
	 */
	EAttribute getContainer_PortableFullPath();

	/**
	 * Returns the meta object for the container reference '{@link prototype.link.model.Container#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Root</em>'.
	 * @see prototype.link.model.Container#getRoot()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Root();

	/**
	 * Returns the meta object for the containment reference list '{@link prototype.link.model.Container#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resources</em>'.
	 * @see prototype.link.model.Container#getResources()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Resources();

	/**
	 * Returns the meta object for class '{@link prototype.link.model.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see prototype.link.model.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for the attribute '{@link prototype.link.model.Resource#getPortableProjectRelativePath <em>Portable Project Relative Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Portable Project Relative Path</em>'.
	 * @see prototype.link.model.Resource#getPortableProjectRelativePath()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_PortableProjectRelativePath();

	/**
	 * Returns the meta object for the container reference '{@link prototype.link.model.Resource#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Container</em>'.
	 * @see prototype.link.model.Resource#getContainer()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_Container();

	/**
	 * Returns the meta object for the containment reference list '{@link prototype.link.model.Resource#getMarkers <em>Markers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Markers</em>'.
	 * @see prototype.link.model.Resource#getMarkers()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_Markers();

	/**
	 * Returns the meta object for class '{@link prototype.link.model.Marker <em>Marker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Marker</em>'.
	 * @see prototype.link.model.Marker
	 * @generated
	 */
	EClass getMarker();

	/**
	 * Returns the meta object for the attribute '{@link prototype.link.model.Marker#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see prototype.link.model.Marker#getId()
	 * @see #getMarker()
	 * @generated
	 */
	EAttribute getMarker_Id();

	/**
	 * Returns the meta object for the container reference '{@link prototype.link.model.Marker#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Resource</em>'.
	 * @see prototype.link.model.Marker#getResource()
	 * @see #getMarker()
	 * @generated
	 */
	EReference getMarker_Resource();

	/**
	 * Returns the meta object for class '{@link prototype.link.model.LinkMarker <em>Link Marker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link Marker</em>'.
	 * @see prototype.link.model.LinkMarker
	 * @generated
	 */
	EClass getLinkMarker();

	/**
	 * Returns the meta object for the reference list '{@link prototype.link.model.LinkMarker#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>To</em>'.
	 * @see prototype.link.model.LinkMarker#getTo()
	 * @see #getLinkMarker()
	 * @generated
	 */
	EReference getLinkMarker_To();

	/**
	 * Returns the meta object for the reference list '{@link prototype.link.model.LinkMarker#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>From</em>'.
	 * @see prototype.link.model.LinkMarker#getFrom()
	 * @see #getLinkMarker()
	 * @generated
	 */
	EReference getLinkMarker_From();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LinksFactory getLinksFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link prototype.link.model.impl.WorkspaceRootImpl <em>Workspace Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see prototype.link.model.impl.WorkspaceRootImpl
		 * @see prototype.link.model.impl.LinksPackageImpl#getWorkspaceRoot()
		 * @generated
		 */
		EClass WORKSPACE_ROOT = eINSTANCE.getWorkspaceRoot();

		/**
		 * The meta object literal for the '<em><b>Containers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKSPACE_ROOT__CONTAINERS = eINSTANCE.getWorkspaceRoot_Containers();

		/**
		 * The meta object literal for the '{@link prototype.link.model.impl.ContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see prototype.link.model.impl.ContainerImpl
		 * @see prototype.link.model.impl.LinksPackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '<em><b>Portable Full Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER__PORTABLE_FULL_PATH = eINSTANCE.getContainer_PortableFullPath();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__ROOT = eINSTANCE.getContainer_Root();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__RESOURCES = eINSTANCE.getContainer_Resources();

		/**
		 * The meta object literal for the '{@link prototype.link.model.impl.ResourceImpl <em>Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see prototype.link.model.impl.ResourceImpl
		 * @see prototype.link.model.impl.LinksPackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '<em><b>Portable Project Relative Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE__PORTABLE_PROJECT_RELATIVE_PATH = eINSTANCE.getResource_PortableProjectRelativePath();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__CONTAINER = eINSTANCE.getResource_Container();

		/**
		 * The meta object literal for the '<em><b>Markers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__MARKERS = eINSTANCE.getResource_Markers();

		/**
		 * The meta object literal for the '{@link prototype.link.model.impl.MarkerImpl <em>Marker</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see prototype.link.model.impl.MarkerImpl
		 * @see prototype.link.model.impl.LinksPackageImpl#getMarker()
		 * @generated
		 */
		EClass MARKER = eINSTANCE.getMarker();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MARKER__ID = eINSTANCE.getMarker_Id();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MARKER__RESOURCE = eINSTANCE.getMarker_Resource();

		/**
		 * The meta object literal for the '{@link prototype.link.model.impl.LinkMarkerImpl <em>Link Marker</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see prototype.link.model.impl.LinkMarkerImpl
		 * @see prototype.link.model.impl.LinksPackageImpl#getLinkMarker()
		 * @generated
		 */
		EClass LINK_MARKER = eINSTANCE.getLinkMarker();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK_MARKER__TO = eINSTANCE.getLinkMarker_To();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK_MARKER__FROM = eINSTANCE.getLinkMarker_From();

	}

} //LinksPackage
