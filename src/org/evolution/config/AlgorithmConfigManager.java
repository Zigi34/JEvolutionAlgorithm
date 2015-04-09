package org.evolution.config;

import java.io.InputStream;
import java.util.Hashtable;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.evolution.EvolAlgorithm;
import org.evolution.util.Constants;
import org.evolution.util.PropertyManager;
import org.evolution.util.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class AlgorithmConfigManager {

	private static final Logger log = Logger
			.getLogger(AlgorithmConfigManager.class);
	private static Hashtable<String, AlgorithmConfigManager> algorithmConfigList;

	private static void initialize() {
		algorithmConfigList = new Hashtable<String, AlgorithmConfigManager>();

		InputStream is = AlgorithmConfigManager.class
				.getResourceAsStream(PropertyManager.getValue(
						Constants.CONFIG_FILE,
						Constants.PROPERTY_CONFIG_CONFIG_MANAGER));
		Document document = Utils.loadXML(is);
		XPath xPath = XPathFactory.newInstance().newXPath();
		try {
			NodeList nodes = (NodeList) xPath.compile(
					"config/config_managers/config_manager[@type='algorithm']")
					.evaluate(document, XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				String classPath = node.getAttributes().getNamedItem("class")
						.getNodeValue();
				String name = node.getAttributes().getNamedItem("name")
						.getNodeValue();
				AlgorithmConfigManager algorithm = null;
				try {
					algorithm = (AlgorithmConfigManager) Utils
							.createInstance(classPath);
				} catch (Exception e) {
					log.error(e);
				}
				if (algorithm != null) {
					algorithmConfigList.put(name, algorithm);
					log.debug(classPath);
				}

			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	public static AlgorithmConfigManager getManager(String name) {
		if (algorithmConfigList == null)
			initialize();
		return algorithmConfigList.get(name);
	}

	public abstract EvolAlgorithm<?> loadConfiguration(Document xml)
			throws XPathExpressionException;

	public abstract Document saveConfiguration(EvolAlgorithm<?> algorithm);

}
