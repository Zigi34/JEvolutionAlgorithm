package org.evolution.config.select;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.evolution.config.Config;
import org.evolution.config.ModelFactory;
import org.evolution.function.select.RouleteWheelSelectFunction;
import org.evolution.util.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class RouleteWheelSelectModel implements ModelFactory {

	public Object loadConfiguration(Node xml) throws XPathExpressionException {
		XPath xPath = Utils.getXPath();

		RouleteWheelSelectFunction selectFunction = new RouleteWheelSelectFunction();
		Node probability = (Node) xPath.compile("probability").evaluate(xml,
				XPathConstants.NODE);
		if (probability != null) {
			selectFunction.setProbability(Double.parseDouble(probability
					.getTextContent()));
		}
		Node max = (Node) xPath.compile("max_select").evaluate(xml,
				XPathConstants.NODE);
		if (max != null) {
			selectFunction
					.setMaxSelected(Integer.parseInt(max.getTextContent()));
		}
		return selectFunction;
	}

	public Node saveConfiguration(Object instance) {
		if (instance instanceof RouleteWheelSelectFunction) {
			RouleteWheelSelectFunction selectFunction = (RouleteWheelSelectFunction) instance;
			Document document = Utils.createDocument();
			Element root = document.createElement("select_function");
			root.setAttribute("model",
					Config.getModelByModel(RouleteWheelSelectModel.class).name);
			Element probabilityElement = document.createElement("probability");
			probabilityElement.setTextContent(String.valueOf(selectFunction
					.getProbability()));
			root.appendChild(probabilityElement);
			if (selectFunction.getMaxSelected() != 0) {
				Element maxSelected = document.createElement("max_select");
				maxSelected.setTextContent(String.valueOf(selectFunction
						.getMaxSelected()));
				root.appendChild(maxSelected);
			}
			return root;

		}
		return null;
	}

}
