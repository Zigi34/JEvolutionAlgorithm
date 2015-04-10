package org.evolution.config.algorithm;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.evolution.EvolAlgorithm;
import org.evolution.config.ModelFactory;
import org.evolution.ga.GeneticAlgorithm;
import org.evolution.util.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class GeneticAlgorithmModel implements ModelFactory {

	public EvolAlgorithm<?> loadConfiguration(Node xml)
			throws XPathExpressionException {

		XPath xPath = XPathFactory.newInstance().newXPath();
		GeneticAlgorithm<?> algorithm = new GeneticAlgorithm();

		Node solutionSpace = (Node) xPath.compile("Algorithm/SolutionSpace")
				.evaluate(xml, XPathConstants.NODE);

		return algorithm;
	}

	public Node saveConfiguration(EvolAlgorithm<?> algorithm) {
		if (algorithm instanceof GeneticAlgorithm<?>) {
			GeneticAlgorithm<?> ga = (GeneticAlgorithm<?>) algorithm;
			Document document = Utils.createDocument();
			Element root = document.createElement("algorithm");
			root.setAttribute("type", getName());

			// SET MAX GENERATION
			Element generation = document.createElement("generation");
			generation.setTextContent(String.valueOf(ga.getTargetGeneration()));
			root.appendChild(generation);

			if (ga.getCrossFunction() != null) {

			}
			return root;
		}
		return null;
	}

	public String getName() {
		return "GeneticAlgorithmModel";
	}
}
