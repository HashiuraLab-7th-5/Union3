package makererrormap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import database.SQLiteSample;
import marker.GetResource;
import property.ViolationPropertyContext;
import views.View;

public class ErrorMessageMaker {

	private static int linenumber;
	private static ArrayList<Map<String,String>> tableData = new ArrayList<Map<String,String>>();
	public ErrorMessageMaker() {

	}

	public static void makeErrorMessage(Table table) {

		SQLiteSample sql = new SQLiteSample();
		ITextFileBufferManager manager = FileBuffers.getTextFileBufferManager();
		String selectpattarn = null;
		View view;
		try {
			view = (View) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("Union3.ErrorView");
			selectpattarn = view.getSelectPattern();
		} catch (PartInitException e1) {
			// TODO �����������ꂽ catch �u���b�N
			e1.printStackTrace();
		}


		String tableName = "union_test"; //仮でAggregationテーブル;

		//SQLiteから検出結果をとってくる
		try {
			sql.conectSample();
			tableData = sql.getTable(tableName);

		} catch (IOException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}


		Iterator it = tableData.iterator();
		while(it.hasNext())
		{
			HashMap<String,String> sqldata = (HashMap<String, String>) it.next();
			String classname = getClassName(sqldata);
			String errorcode = getErrorCode(sqldata);
			String start = getStartError(sqldata);
			String end = getEndError(sqldata);

			String[] data = new String[3];
			TableItem item = new TableItem(table,SWT.NULL);

			data[0] = classname; // テーブルに入れるクラス名

			//行数
			getLineNumber(classname, Integer.parseInt(start));

			//違反理由とってくる
			ViolationPropertyContext context = new ViolationPropertyContext(selectpattarn,errorcode);
			ArrayList<String> violationList = context.getErrorViolation();
			String error_violation = violationList.get(2);

			//Tableへ追加
			item.setText(0,classname);
			item.setText(1,String.valueOf(linenumber));
			item.setText(2,error_violation);


		}


	}

	private static String getLineNumber(String classname, int start) {

		String projectname = new String();
		IResource project_resource = null;

		//プロジェクトの名前をとってくる
		projectname = GetResource.getProjectname();
		project_resource = GetResource.getResource(projectname);

		try {
			project_resource.accept(new IResourceVisitor() {
				public boolean visit(IResource resource) throws CoreException {
					if(resource.getType() == IResource.FILE) {
						String ext = resource.getFileExtension();
						if(ext != null)
						{
							// javaファイルでありデータベースの中クラスの名前と同じかを調べる
					    	if(ext.equals("java") && resource.getName().equals(classname))
					    	{
					    		IPath path = resource.getFullPath();
					    		ITextFileBufferManager manager = FileBuffers.getTextFileBufferManager();
					    		manager.connect(path, LocationKind.IFILE,null);
					    		ITextFileBuffer buffer = manager.getTextFileBuffer(path,LocationKind.IFILE);

					    		IDocument document = buffer.getDocument();

					    		//行数を調べる
					    		FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
					    		try {
									linenumber = document.getLineOfOffset(start);
								} catch (BadLocationException e) {
									// TODO �����������ꂽ catch �u���b�N
									e.printStackTrace();
								}

					    	}
						}
					}
					return true;
				}
			});
		} catch (CoreException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}
		return null;

	}


	private static String getClassName(HashMap<String,String> sqldata) {

		return sqldata.get("classname");

	}

	private static String getErrorCode(HashMap<String,String> sqldata) {

		return sqldata.get("errorcode");

	}

	private static String getStartError(HashMap<String,String> sqldata) {

		return sqldata.get("start");

	}

	private static String getEndError(HashMap<String,String> sqldata) {

		return sqldata.get("end");

	}
}
