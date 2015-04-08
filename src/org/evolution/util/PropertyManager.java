package org.evolution.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyManager {

	private static Hashtable<String, Properties> propertiesCache = new Hashtable<String, Properties>();
	private static final Logger log = Logger.getLogger(PropertyManager.class);

	/**
	 * Return value from property file with selected key
	 * 
	 * @param propertyFile
	 *            path to property file
	 * @param key
	 *            property name
	 * @return property value
	 */
	public static String getValue(String propertyFile, String key) {
		if (!propertiesCache.containsKey(propertyFile)) {
			loadProperties(propertyFile);
		}

		Properties property = propertiesCache.get(propertyFile);
		return property.getProperty(key);
	}

	/**
	 * Load property file from disc to memory and save it to dictionary (cache)
	 * 
	 * @param propertyFile
	 *            cesta k property souboru
	 */
	private static void loadProperties(String propertyFile) {
		Properties prop = new Properties();

		InputStream inputStream = PropertyManager.class
				.getResourceAsStream(propertyFile);

		if (inputStream != null) {
			try {
				prop.load(inputStream);
				propertiesCache.put(propertyFile, prop);
			} catch (IOException e) {
				log.error(String.format("Property file '%s' cannot be loaded!",
						propertyFile));
			}
		}
	}
}
