package MatchProcess;

import java.util.HashMap;
import java.util.Map;

import test.Pattern_IO;

public class MatchProcess {
	public void process(Map map) {
		Map<String,MP_Strategy> mps = new HashMap<>();
		mps.put("Factory Method", new MP_FactoryMethod());
		mps.put("Adapter", new MP_Adapter());
		mps.put("Conposite", new MP_Composite());
		
		Pattern_IO pio = new Pattern_IO();
		MP_Select mp = new MP_Select(mps.get(pio.p_read()));
		mp.s_match(map);
	}

}
