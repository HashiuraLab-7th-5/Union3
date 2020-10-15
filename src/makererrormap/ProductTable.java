package makererrormap;

import java.util.ArrayList;
import java.util.Map;

import org.eclipse.swt.widgets.Table;

public interface ProductTable {
	abstract public Table getWidget();

	public abstract void makeTableData(ArrayList<Map<String,String>> tabledata);
}
