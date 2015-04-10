package org.evolution.config;

import javax.xml.xpath.XPathExpressionException;

import org.evolution.EvolAlgorithm;
import org.w3c.dom.Node;

public interface ModelFactory {

	public EvolAlgorithm<?> loadConfiguration(Node xml)
			throws XPathExpressionException;

	public Node saveConfiguration(EvolAlgorithm<?> algorithm);

	public String getName();
}
