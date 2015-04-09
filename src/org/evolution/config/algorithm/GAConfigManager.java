package org.evolution.config.algorithm;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.evolution.EvolAlgorithm;
import org.evolution.config.AlgorithmConfigManager;
import org.evolution.ga.GeneticAlgorithm;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class GAConfigManager extends AlgorithmConfigManager {

	@Override
	public EvolAlgorithm<?> loadConfiguration(Document xml)
			throws XPathExpressionException {

		XPath xPath = XPathFactory.newInstance().newXPath();

		GeneticAlgorithm<?> algorithm = new GeneticAlgorithm();

		Node solutionSpace = (Node) xPath.compile("Algorithm/SolutionSpace")
				.evaluate(xml, XPathConstants.NODE);
		if (solutionSpace != null) {

		}

		return algorithm;
	}

	@Override
	public Document saveConfiguration(EvolAlgorithm<?> algorithm) {
		return null;
	}

}
