package org.evolution.util;

import java.io.InputStream;
import java.util.Hashtable;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.evolution.config.AlgorithmConfigManager;
import org.evolution.config.model.EvolAlgorithmModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Config {
	private static Hashtable<String, EvolAlgorithmModel> algorithmConfigs;
	private static final Logger log = Logger.getLogger(Config.class);

	private static void initialize() {
		algorithmConfigs = new Hashtable<String, EvolAlgorithmModel>();

		InputStream is = AlgorithmConfigManager.class
				.getResourceAsStream(PropertyManager.getValue(
						Constants.CONFIG_FILE,
						Constants.PROPERTY_CONFIG_CONFIG_MANAGER));
		Document document = Utils.loadXML(is);
		XPath xPath = XPathFactory.newInstance().newXPath();
		try {
			NodeList nodes = (NodeList) xPath.compile(
					"config/algorithms/algorithm").evaluate(document,
					XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				EvolAlgorithmModel algorithm = new EvolAlgorithmModel();
				algorithm.name = node.getAttributes().getNamedItem("name")
						.getNodeValue();
				NodeList list = node.getChildNodes();
				for (int j = 0; j < list.getLength(); j++)
					if (list.item(j).getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) list.item(j);
						algorithm.configManager = element.getTextContent();
					}
				algorithm.classPath = node.getAttributes()
						.getNamedItem("class").getNodeValue();
				algorithmConfigs.put(algorithm.name, algorithm);
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	public static EvolAlgorithmModel getAlgorithm(String name) {
		if (algorithmConfigs == null)
			initialize();
		return algorithmConfigs.get(name);
	}
}
