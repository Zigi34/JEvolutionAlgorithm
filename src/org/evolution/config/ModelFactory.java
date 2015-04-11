package org.evolution.config;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Node;

public interface ModelFactory {

	public Object loadConfiguration(Node xml) throws XPathExpressionException;

	public Node saveConfiguration(Object instance);
}
