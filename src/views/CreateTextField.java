package views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class CreateTextField {
	Text text;
	GridData gd;

	public CreateTextField(Composite container){
		gd = new GridData();
		gd.horizontalSpan = 1;
		text = new Text(container,SWT.SINGLE);
		text.setText("ディレクトリ");
		text.setLayoutData(gd);
	}

	public Text getText() {
		return text;
	}
}
