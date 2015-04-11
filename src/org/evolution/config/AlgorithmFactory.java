package org.evolution.config;

import java.io.File;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.evolution.EvolAlgorithm;
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
			String model = (String) Utils.getXPath()
					.compile("algorithm/@model")
					.evaluate(doc, XPathConstants.STRING);
			ModelFactory factory = Config.getModelByName(model).createFactory();
			if (factory != null) {
				return (EvolAlgorithm<?>) factory.loadConfiguration(doc);
			}
		} catch (XPathExpressionException e) {
			log.error(e);
		}
		return null;
	}

	public static void save(EvolAlgorithm<?> algorithm, File file) {
		ModelFactory factory = Config.getModelByInstance(algorithm.getClass())
				.createFactory();
		Document document = Utils.createDocument();
		Node root = factory.saveConfiguration(algorithm);
		document.appendChild(document.importNode(root, true));
		Utils.saveXML(document, file);
	}
}
