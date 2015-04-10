package org.evolution.config;

import java.io.File;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.evolution.EvolAlgorithm;
import org.evolution.util.Config;
import org.evolution.util.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public abstract class AlgorithmFactory {

	private static final Logger log = Logger.getLogger(AlgorithmFactory.class);

	private AlgorithmFactory() {
	}

	public static EvolAlgorithm<?> load(File file) {
		Document doc = Utils.loadXML(file);
		try {
			Node algorithm = (Node) Utils.getXPath().compile("algorithm")
					.evaluate(doc, XPathConstants.NODE);
			String factoryName = Utils.getAttributeValue(algorithm, "type");
			ModelFactory factory = Config.getModel(factoryName).manager;
			if (factory != null) {
				return factory.loadConfiguration(doc);
			}
		} catch (XPathExpressionException e) {
			log.error(e);
		}
		return null;
	}

	public static void save(EvolAlgorithm<?> algorithm, File file) {
		ModelFactory factory = Config.getModel(algorithm.getModel()).manager;
		Document document = Utils.createDocument();
		Node root = factory.saveConfiguration(algorithm);
		Node firstDocImportedNode = document.importNode(root, true);
		document.appendChild(firstDocImportedNode);
		Utils.saveXML(document, file);
	}
}
