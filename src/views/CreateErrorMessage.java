package views;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.IGotoMarker;

import marker.JumpMarker;

public class CreateErrorMessage {
	private Composite container;
	Table table;
	GridData gd = new GridData();
	private String[] data = {"クラス名","行数","違反理由と解決策"};

	public CreateErrorMessage(Composite container) {

		this.container = container;
		gd.heightHint = 200;
		gd.widthHint = 700;
		gd.horizontalSpan = 2;

	}

	public Table getTable() {
		TableViewer viewer = new TableViewer(container,SWT.BORDER | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);

		table = viewer.getTable();
		table.setLayoutData(gd);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		for(int i = 0; i < data.length; i++)
		{
			TableColumn col = new TableColumn(table,SWT.LEFT);
			col.setText(data[i]);
			col.setWidth(200);
		}

		TableItem item = new TableItem(table,SWT.NONE);
		item.setText(0, "test");
		item.setText(1, "5");
		item.setText(2, "Inheritance:Line\r\n" + "ここに違反理由と解決策");

		table.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int select = table.getSelectionIndex();
				TableItem[] item = table.getSelection();
				System.out.println(item[0].getText(0) + " " + item[0].getText(1) + " " + item[0].getText(2));

				JumpMarker jump = new JumpMarker();
				jump.getMarker(item[0].getText(2));
			}
		});

		return table;
	}
/*
	//�W�����v���Ă݂�
	String projectName = "WinRate2";
	IWorkspace workspace = ResourcesPlugin.getWorkspace();
	IWorkspaceRoot root = workspace.getRoot();
	IProject project = root.getProject(projectName);
	IResource resource = (IResource)project.getAdapter(IResource.class);
	try {
		resource.accept(new IResourceVisitor() {
			public boolean visit(IResource resource) throws CoreException {
				if(resource.getName().equals(item[0].getText(0) + ".java")) {
					System.out.println("�����܂ł��Ă邩");
					IMarker[] markers = null;
//					try {
//						markers = resource.findMarkers("FileMarker",true,IResource.DEPTH_INFINITE);
//						System.out.println("markers�̂��try�̒��g" + markers);
//					} catch (CoreException e1) {
//						// TODO �����������ꂽ catch �u���b�N
//						e1.printStackTrace();
//					}
					try {
						String structure = "Inheritance";
						String rule = "Line";
						for(IMarker m : resource.findMarkers(IMarker.PROBLEM, true,IResource.DEPTH_INFINITE)){
							if(((String) m.getAttribute(IMarker.MESSAGE)).startsWith(structure + ":" + rule)) {
								System.out.println("markers�̂��try�̒��g" + m);
								//�t�@�C������A�N�e�B�u�G�f�B�^�̎擾
								IWorkbench workbench = PlatformUI.getWorkbench();
								IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
								IWorkbenchPage page = window.getActivePage();
								IEditorPart editor = IDE.openEditor(page, (IFile)resource);
								IDE.gotoMarker(editor, m);
							}
						}
					} catch (CoreException e1) {
						// TODO �����������ꂽ catch �u���b�N
						e1.printStackTrace();
					}
				}
				return true;
			}
		});
	} catch (CoreException e2) {
		// TODO �����������ꂽ catch �u���b�N
		e2.printStackTrace();
	}
*/


}