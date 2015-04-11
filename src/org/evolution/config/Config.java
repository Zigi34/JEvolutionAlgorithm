package org.evolution.config;

import java.io.InputStream;
import java.util.Hashtable;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.evolution.util.Constants;
import org.evolution.util.PropertyManager;
import org.evolution.util.Utils;
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
					ConfigModel model = new ConfigModel();
					model.name = node.getAttributes().getNamedItem("name")
							.getNodeValue();
					model.model = node.getAttributes().getNamedItem("model")
							.getNodeValue();
					model.type = node.getAttributes().getNamedItem("type")
							.getNodeValue();
					model.instance = node.getAttributes()
							.getNamedItem("instance").getNodeValue();

					models.put(model.name, model);
					log.debug(model.name);
				} catch (Exception e) {
					log.error(e);
				}
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	public static ConfigModel getModelByName(String name) {
		if (models == null)
			initialize();
		return models.get(name);
	}

	public static ConfigModel getModelByInstance(Class<?> clazz) {
		if (models == null)
			initialize();
		for (String name : models.keySet()) {
			ConfigModel model = models.get(name);
			if (model.instance.equals(clazz.getName()))
				return model;
		}
		return null;
	}

	public static ConfigModel getModelByModel(Class<?> clazz) {
		if (models == null)
			initialize();
		for (String name : models.keySet()) {
			ConfigModel model = models.get(name);
			if (model.model.equals(clazz.getName()))
				return model;
		}
		return null;
	}
}
