package org.evolution.config.population.solution;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.evolution.config.Config;
import org.evolution.config.ConfigModel;
import org.evolution.config.ModelFactory;
import org.evolution.population.solution.ArraySolution;
import org.evolution.util.Utils;
import org.evolution.value.NumericValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ArraySolutionModel implements ModelFactory {

	public Object loadConfiguration(Node xml) throws XPathExpressionException {
		XPath xPath = Utils.getXPath();

		ArraySolution<NumericValue> solution = new ArraySolution<NumericValue>();
		NodeList values = (NodeList) xPath.compile("value").evaluate(xml,
				XPathConstants.NODESET);
		if (values != null) {
			for (int i = 0; i < values.getLength(); i++) {
				Element value = (Element) values.item(i);
				solution.addValue(new NumericValue(Double.parseDouble(value
						.getTextContent())));
			}
		}
		Node fitness = (Node) xPath.compile("fitness").evaluate(xml,
				XPathConstants.NODE);
		if (fitness != null) {
			solution.setFitness(Double.parseDouble(fitness.getTextContent()));
		}
		return solution;
	}

	public Node saveConfiguration(Object instance) {
		if (instance instanceof ArraySolution<?>) {
			ArraySolution<NumericValue> solution = (ArraySolution<NumericValue>) instance;
			Document document = Utils.createDocument();
			Element root = document.createElement("solution");
			ConfigModel model = Config
					.getModelByModel(ArraySolutionModel.class);
			root.setAttribute("model", model.name);

			for (NumericValue value : solution.getAll()) {
				Element valueElement = document.createElement("value");
				valueElement.setTextContent(String.valueOf(value.getDouble()));
				root.appendChild(valueElement);
			}
			Element fitnessElement = document.createElement("fitness");
			fitnessElement
					.setTextContent(String.valueOf(solution.getFitness()));
			root.appendChild(fitnessElement);

			return root;
		}
		return null;
	}

}
