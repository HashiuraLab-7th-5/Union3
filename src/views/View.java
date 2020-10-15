package views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import makererrormap.ErrorMapMaker;
import makererrormap.ErrorMessageMaker;


public class View extends ViewPart {
//	Table ErrorMap;
	Table ErrorMessage;
	Button button;
	Combo combo;
	Text text;
	TabFolder tabFolder;
	String path;
	//CreateErrorMap cTable;
	CreateErrorMessage cMessage;
	CreateDirectoryButton cButton;
	CreateComboBox cCombo;
	CreateTabFolder cTabFolder;


	Composite container;


	public View() {
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		System.out.println("View�̃R���X�g���N�^����");
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		container = new Composite(parent,SWT.NONE);
		container.setLayout(new GridLayout(2,true));

		//タブフォルダー
		cTabFolder = new CreateTabFolder(container);
		tabFolder = cTabFolder.gteTabFolder();

		//cTable = new CreateErrorMap(container);
		cMessage = new CreateErrorMessage(container);
		//CreateTextField cText = new CreateTextField(container);
		cButton = new CreateDirectoryButton(container);
		cCombo = new CreateComboBox(container);


		ErrorMessage = cMessage.getTable();
		combo = cCombo.getCombo();
		button = cButton.getDirectoryButton();
		text = cButton.getText();
	}

	public void updateTable() {
		ErrorMapUpdate();
		ErrorMessageUpdate();
	}

	private void ErrorMapUpdate() {

		ErrorMapMaker.makeErrorMap(container, tabFolder);

	}

	private void ErrorMessageUpdate() {

		//�f�[�^�x�[�X����Ȃ񂩂���ǂݎ����
		ErrorMessageMaker.makeErrorMessage(ErrorMessage);
	}

	//Table��Colum�����ׂď���
	public void columsDispone() {

		ErrorMapDispone();
		ErrorMessageDispone();

	}

	private void ErrorMapDispone() {

		//Mapの初期化
		TabItem[] ts = tabFolder.getItems();

		for(int i = 0; i < ts.length; i++)
		{
			ts[i].dispose();
		}

	}

	private void ErrorMessageDispone() {
		ErrorMessage.setRedraw(false);

		ErrorMessage.removeAll();

		ErrorMessage.setRedraw(true);
	}

	//combo����I������Ă�����̂��擾
	public String getSelectPattern() {
		return combo.getText();
	}

	public String getDirectory() {
		return text.getText();
	}

	@Override
	public void setFocus() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}
}