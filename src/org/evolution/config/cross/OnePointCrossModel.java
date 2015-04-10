package org.evolution.config.cross;

import javax.xml.xpath.XPathExpressionException;

import org.evolution.EvolAlgorithm;
import org.evolution.config.ModelFactory;
import org.w3c.dom.Node;

public class OnePointCrossModel implements ModelFactory {

	public EvolAlgorithm<?> loadConfiguration(Node xml)
			throws XPathExpressionException {
		return null;
	}

	public Node saveConfiguration(EvolAlgorithm<?> algorithm) {
		return null;
	}

	public String getName() {
		return "OnePointCrossModel";
	}

}
