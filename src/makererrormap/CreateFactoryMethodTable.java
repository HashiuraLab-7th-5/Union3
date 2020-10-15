package makererrormap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import marker.JumpMarker;
import property.MapProperty;

public class CreateFactoryMethodTable implements ProductTable{

	private Composite container;
	private TabFolder tabfolder;
	private GridData gd = new GridData();

	final String MicroStructure[] = {"Inheritance", "Aggregation", "SubInstance"};
	final String Lole[] = {"Micro-Structure�_Class","Creator","Product","Concrete Creator","Concrete Product"};
	String[][] TableData = {
			{" "," ","〇","〇"},
			{"〇","　","　","　"},
			{"　","　","〇","　"},
	};

	public CreateFactoryMethodTable(Composite container,TabFolder tabfolder) {

		this.tabfolder = tabfolder;
		this.container = container;
		gd.heightHint = 200;
		gd.widthHint = 650;
		gd.horizontalSpan = 2;

	}

	public void makeTableData(ArrayList<Map<String,String>> tabledata) {

		if(tabledata != null) {

			MapProperty mapproperty = MapProperty.getInstance();
			Iterator it = tabledata.iterator();

			while(it.hasNext()) {
				Map<String,String> data = (Map<String,String>) it.next();

				String errorcode = data.get("errorcode");
				String[] positionList = mapproperty.crossperty("Factory Method", errorcode).split(":");
				int height = Integer.parseInt(positionList[0]);
				int width = Integer.parseInt(positionList[1]);
				TableData[height][width] = "�~";

			}
		}

	}


	private String[] splitCrossProperty(String clossposition) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return clossposition.split(":");
	}

	public Table getWidget() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		Table table;// = new Table(tabfolder,SWT.NONE);
		TableViewer viewer = new TableViewer(tabfolder,SWT.BORDER | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);

		table = viewer.getTable();
		table.setLayoutData(gd);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		for(int i = 0; i < Lole.length; i++)
		{
			TableColumn col = new TableColumn(table,SWT.LEFT);
			col.setText(Lole[i]);
			col.setWidth(125);
		}

		for(int i = 1; i <= MicroStructure.length; i++)
		{
			TableItem item = new TableItem(table,SWT.NONE);
			item.setText(0, MicroStructure[i-1]);
			for(int j = 1; j <= 4; j++)
			{
				item.setText(j,TableData[i - 1][j - 1]);
			}
		}

		table.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {

				Point point = new Point(e.x, e.y);
				Table table = (Table) e.getSource();
				TableItem item = table.getItem(point);


				if(item == null) {
					System.out.println("null");
					return;
				}
				for(int columNum = 0; columNum < table.getColumnCount();columNum++) {
					//Column�̈ʒu��for�ŋ��߂�
					if(item.getBounds(columNum).contains(point)) {

						JumpMarker jump = new JumpMarker();
						jump.getMarker(item.getText(0),Lole[columNum]);
						System.out.println("��:" + columNum);
						System.out.println(item.getText(columNum));
						break;

					}
				}
			}
		});

		return table;
	}

	//CrossProsition�̃f�[�^�𕪊�����p
	public String[] splitCrossPosition(String cross) {

		return cross.split(":");

	}

}
