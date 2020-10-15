package makererrormap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;

import database.SQLiteSample;

public class ErrorMapMaker {

	private ErrorMapMaker() {

	}

	public static void makeErrorMap(Composite container,TabFolder tabFolder) {

		//ErrorMapÇ÷ÇÃÉfÅ[É^í«â¡
		CreateTab tab = new CreateTab(container,tabFolder);
		tab.createTab();

	}
}
