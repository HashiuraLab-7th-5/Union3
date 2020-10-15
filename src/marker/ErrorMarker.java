package marker;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import union3.Activator;

public class ErrorMarker {

	public static final String MARKER_ID = Activator.PLUGIN_ID + ".Union3";

	public static IMarker addMarker(IResource resource, int start, int end, String message)
	{
		IMarker marker = null;

		try {
			marker = resource.createMarker(MARKER_ID);
			marker.setAttribute(IMarker.TRANSIENT, true);
			marker.setAttribute(IMarker.CHAR_START, start);
			marker.setAttribute(IMarker.CHAR_END, end);
			//marker.setAttribute(IMarker.LINE_NUMBER, line);  �s���͂���Ȃ��݂���
			marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
			marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
			marker.setAttribute(IMarker.MESSAGE, message);

		} catch (CoreException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
		return marker;
	}


	public static void deleteMarker(IResource resource)
	{
		try {
        	resource.deleteMarkers(MARKER_ID, false, IResource.DEPTH_ZERO);  // �}�[�J�[���폜
        } catch (CoreException e) {
        	e.printStackTrace();
        }
	}
}
