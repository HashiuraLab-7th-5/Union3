package makererrormap;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Table;

public abstract class Creator {
	public ProductTable createTable() {
		ProductTable producttable = createProduct();
		return producttable;
	}
	public abstract ProductTable createProduct();
}
