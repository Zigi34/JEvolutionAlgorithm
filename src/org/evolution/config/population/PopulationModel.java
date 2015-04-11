package org.evolution.config.population;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.evolution.config.Config;
import org.evolution.config.ConfigModel;
import org.evolution.config.ModelFactory;
import org.evolution.population.Population;
import org.evolution.population.solution.ArraySolution;
import org.evolution.population.solution.Solution;
import org.evolution.util.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PopulationModel implements ModelFactory {

	public Object loadConfiguration(Node popNode)
			throws XPathExpressionException {
		XPath xPath = Utils.getXPath();

		Population population = new Population();
		// POPULATION
		if (popNode != null) {
			Node maxPop = popNode.getAttributes().getNamedItem("max");
			if (maxPop != null)
				population.setMaxIndividuals(Integer.parseInt(maxPop
						.getTextContent()));
			NodeList solutions = (NodeList) xPath.compile("solution").evaluate(
					popNode, XPathConstants.NODESET);
			if (solutions != null) {
				for (int i = 0; i < solutions.getLength(); i++) {
					Element solution = (Element) solutions.item(i);
					String typeName = solution.getAttribute("model");

					ModelFactory factory = Config.getModelByName(typeName)
							.createFactory();
					if (factory != null) {
						Object result = factory.loadConfiguration(solution);
						if (result != null)
							population.add((Solution) result);
					}
				}
			}
		}

		return population;
	}

	public Node saveConfiguration(Object instance) {
		if (instance instanceof Population) {
			Population population = (Population) instance;
			Document document = Utils.createDocument();
			Element root = document.createElement("population");
			ConfigModel model = Config.getModelByModel(PopulationModel.class);
			root.setAttribute("model", model.name);
			root.setAttribute("max",
					String.valueOf(population.getMaxIndividuals()));

			for (Solution solution : population) {
				if (solution instanceof ArraySolution<?>) {
					ModelFactory factory = Config.getModelByInstance(
							ArraySolution.class).createFactory();
					root.appendChild(document.importNode(
							factory.saveConfiguration(solution), true));
				}
			}
			return root;
		}
		return null;
	}
}
