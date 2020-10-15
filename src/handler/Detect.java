package handler;

import java.io.IOException;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import marker.CreateMarker;
import marker.DeleteMarker;
import test.Main;
import views.View;


public class Detect implements IHandler {

	Main mainObj = new Main();
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
	}

	@Override
	public void dispose() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		System.out.println("execute");

		/*
		 * 検出のプログラムをここに書いてほしい
		 * ディレクトリが取得したい場合は↓を使ってください。
		 * Textフィールドに表示されてるディレクトリがStringで取得できます
		 * View view = (View) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("Union.ErrorView");
		 * String directory = view.getDirectory();
		 *
		 * 選択されたパターンがとりたい時は↓
		 * String patternname = view.getPattern();
		 */
		//検出の開始
		try {
			mainObj.process();
		} catch (IOException | PartInitException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//可視化
		update();

		return null;
	}

	private void update() {

		try {
			// �m�F�p
			View view = (View) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("Union3.ErrorView");
			System.out.println(view.getDirectory());

//			view.columsDispone();			// Table削除
//			System.out.println("テーブルの削除");
//			view.updateTable();				// Table追加
//			System.out.println("テーブルの追加");
//			DeleteMarker.deleteMarker();	// マーカー削除
//			System.out.println("マーカーの削除");
//			CreateMarker.createMarker();	// マーカー追加
//			System.out.println("マーカーの追加");


		} catch (PartInitException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}

	}

	@Override
	public boolean isEnabled() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

}
