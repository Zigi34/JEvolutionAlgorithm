package org.evolution.util;

import java.io.InputStream;
import java.util.Hashtable;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.evolution.config.AlgorithmFactory;
import org.evolution.config.ModelFactory;
import org.evolution.config.model.ConfigModel;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Config {
	private static Hashtable<String, ConfigModel> models;
	private static final Logger log = Logger.getLogger(Config.class);

	static {
		initialize();
	}

	/**
	 * Initialize all algorithm factories from XML
	 */
	private static void initialize() {
		models = new Hashtable<String, ConfigModel>();

		InputStream is = AlgorithmFactory.class
				.getResourceAsStream(PropertyManager.getValue(
						Constants.CONFIG_FILE,
						Constants.PROPERTY_CONFIG_CONFIG_MANAGER));
		Document document = Utils.loadXML(is);
		XPath xPath = XPathFactory.newInstance().newXPath();
		try {
			NodeList nodes = (NodeList) xPath.compile(
					"config/config_managers/config_manager").evaluate(document,
					XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); i++) {
				try {
					Node node = nodes.item(i);
					String classPath = node.getAttributes()
							.getNamedItem("class").getNodeValue();

					ConfigModel model = new ConfigModel();
					model.manager = (ModelFactory) Utils
							.createInstance(classPath);
					model.type = node.getAttributes().getNamedItem("type")
							.getNodeValue();

					models.put(model.manager.getName(), model);
					log.debug(classPath);
				} catch (Exception e) {
					log.error(e);
				}
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	public static ConfigModel getModel(String name) {
		if (models == null)
			initialize();
		return models.get(name);
	}
}
