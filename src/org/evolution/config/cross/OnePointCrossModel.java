package org.evolution.config.cross;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.evolution.config.Config;
import org.evolution.config.ModelFactory;
import org.evolution.function.cross.OnePointCrossFunction;
import org.evolution.population.solution.ArraySolution;
import org.evolution.util.Utils;
import org.evolution.value.NumericValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class OnePointCrossModel implements ModelFactory {

	public Object loadConfiguration(Node xml) throws XPathExpressionException {
		XPath xPath = Utils.getXPath();

		OnePointCrossFunction<ArraySolution<NumericValue>> crossFunction = new OnePointCrossFunction<ArraySolution<NumericValue>>();
		Node probability = (Node) xPath.compile("probability").evaluate(xml,
				XPathConstants.NODE);
		if (probability != null) {
			crossFunction.setProbability(Double.parseDouble(probability
					.getTextContent()));
		}
		return crossFunction;
	}

	public Node saveConfiguration(Object crossFunction) {
		if (crossFunction instanceof OnePointCrossFunction<?>) {
			OnePointCrossFunction<ArraySolution<NumericValue>> cross = (OnePointCrossFunction<ArraySolution<NumericValue>>) crossFunction;
			Document document = Utils.createDocument();
			Element root = document.createElement("cross_function");
			root.setAttribute("model",
					Config.getModelByModel(OnePointCrossModel.class).name);
			Element probabilityElement = document.createElement("probability");
			probabilityElement.setTextContent(String.valueOf(cross
					.getProbability()));
			root.appendChild(probabilityElement);
			return root;

		}
		return null;
	}
}
