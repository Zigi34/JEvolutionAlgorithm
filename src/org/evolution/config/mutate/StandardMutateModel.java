package org.evolution.config.mutate;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.evolution.config.Config;
import org.evolution.config.ModelFactory;
import org.evolution.function.mutate.StandardMutateFunction;
import org.evolution.population.solution.ArraySolution;
import org.evolution.util.Utils;
import org.evolution.value.NumericValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class StandardMutateModel implements ModelFactory {

	public Object loadConfiguration(Node xml) throws XPathExpressionException {
		XPath xPath = Utils.getXPath();

		StandardMutateFunction<ArraySolution<NumericValue>> mutateFunction = new StandardMutateFunction<ArraySolution<NumericValue>>();
		Node probability = (Node) xPath.compile("probability").evaluate(xml,
				XPathConstants.NODE);
		if (probability != null) {
			mutateFunction.setProbability(Double.parseDouble(probability
					.getTextContent()));
		}
		return mutateFunction;
	}

	public Node saveConfiguration(Object instance) {
		if (instance instanceof StandardMutateFunction<?>) {
			StandardMutateFunction<ArraySolution<NumericValue>> mutateFunction = (StandardMutateFunction<ArraySolution<NumericValue>>) instance;
			Document document = Utils.createDocument();
			Element root = document.createElement("mutate_function");
			root.setAttribute("model",
					Config.getModelByModel(StandardMutateModel.class).name);
			Element probabilityElement = document.createElement("probability");
			probabilityElement.setTextContent(String.valueOf(mutateFunction
					.getProbability()));
			root.appendChild(probabilityElement);
			return root;

		}
		return null;
	}

}
