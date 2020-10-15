package SelectPattern;

import java.io.File;
import java.util.ArrayList;

public class Select {
	private Strategy strategy;
	private ArrayList<String> list = new ArrayList<String>();

	public Select(Strategy strategy) {
		this.strategy = strategy;
	}

	public void fileprocess(File[] filelist) {

		if(filelist != null)
		{
			System.out.println(strategy.FileNameProcessing(filelist));
			this.list = strategy.FileNameProcessing(filelist);
		}
		else {
			System.out.println("filelistはnull");
		}
	}

	public void provisionalnumber() {
		strategy.ProvisionalNumber();
	}

	public void ncombination() {
		strategy.nCombination(list);
	}

}
