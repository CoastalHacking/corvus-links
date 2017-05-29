package prototype.link.impl;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import prototype.link.api.LinkController;
import prototype.link.model.LinksFactory;
import prototype.link.model.LinksPackage;
import prototype.link.model.WorkspaceRoot;

@Component(service=LinkController.class)
public class LinkControllerComponent extends AbstractLinkControllerImpl {

	@Activate
	void activate(BundleContext bc, Map<String,Object> config) {
		final ResourceSet resourceSet = new ResourceSetImpl(); 
		final File file = bc.getBundle().getDataFile("model.links");
		LinksPackage.eINSTANCE.eClass(); // loads the package in the registry
		final URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource resource;
		if (file.exists() && file.length() > 0) {
			resource = resourceSet.getResource(uri, true);
			root = (WorkspaceRoot)resource.getContents().get(0);
		} else {
			resource = resourceSet.createResource(uri);
			root = LinksFactory.eINSTANCE.createWorkspaceRoot();
			resource.getContents().add(root);
		}
		
	}

	@Reference
	void setWorkspace(IWorkspace workspace) {
		this.workspace = workspace;
	}
	void unsetWorkspace(IWorkspace workspace) {
		this.workspace = null;
	}

	@Deactivate
	void deactivate() {
		try {
			if (root != null)
				root.eResource().save(null);
		} catch (IOException e) {
			// TODO log
			e.printStackTrace();
		}
	}

}
