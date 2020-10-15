package marker;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;


public class DeleteMarker {

	public static void deleteMarker(){
		String projectName = new String();
		projectName = GetResource.getProjectname();


		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject project = root.getProject(projectName);
		IResource resource = (IResource)project.getAdapter(IResource.class);

		try {
			resource.accept(new IResourceVisitor() {
				public boolean visit(IResource resource) throws CoreException {
					if(resource.getType() == IResource.FILE)
					{
						String ext = resource.getFileExtension();
				    	if(ext != null)
				    	{
					    	if(ext.equals("java"))	//Ç™ÇŒÇ™ÇŒâﬂÇ¨
					    		ErrorMarker.deleteMarker(resource);
				    	}
					}
					return true;
				}
			});
		} catch (CoreException e) {
			// TODO é©ìÆê∂ê¨Ç≥ÇÍÇΩ catch ÉuÉçÉbÉN
			e.printStackTrace();
		}

	    ErrorMarker.deleteMarker(resource);

	}

}
