package property;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MapProperty {

	private static MapProperty mapproperty = new MapProperty();
	protected InputStream inputStream;
	protected final String START_FILE_PATH = "properties/";
	protected final String extension = ".properties";
	protected final Properties properties = new Properties();
	protected final String fileName = "CrossPosition";
	private String filePath = new String();


	private MapProperty() {

	}

	public static MapProperty getInstance() {
		return mapproperty;
	}

	public final String crossperty(String patternName, String key) {

		String crossposition;

		openProperty(patternName);
		crossposition = getProperty(key);

		return crossposition;
	}

	private final void openProperty(String patternName) {
		try {

	        filePath = START_FILE_PATH + patternName + "/" + fileName + extension;
	        inputStream = ViolationProperty.class.getClassLoader().getResourceAsStream(filePath);
            properties.load(inputStream);

		} catch (IOException e) {
			// TODO é©ìÆê∂ê¨Ç≥ÇÍÇΩ catch ÉuÉçÉbÉN
			e.printStackTrace();
		}
	}


	private String getProperty (final String key) {
		return properties.getProperty(key);
	}
}
