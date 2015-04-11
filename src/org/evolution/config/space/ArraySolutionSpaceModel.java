package org.evolution.config.space;

import java.util.LinkedList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.evolution.config.Config;
import org.evolution.config.ModelFactory;
import org.evolution.population.solution.ArraySolution;
import org.evolution.space.ArraySolutionSpace;
import org.evolution.space.bounds.ArrayIndividualBound;
import org.evolution.space.bounds.SolutionBound;
import org.evolution.util.Bound;
import org.evolution.util.Utils;
import org.evolution.value.NumericValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ArraySolutionSpaceModel implements ModelFactory {

	public Object loadConfiguration(Node xml) throws XPathExpressionException {
		XPath xPath = Utils.getXPath();

		String dimension = (String) xPath.compile("@size").evaluate(xml,
				XPathConstants.STRING);
		if (dimension != null) {
			ArraySolutionSpace space = new ArraySolutionSpace(
					Integer.parseInt(dimension));

			NodeList bounds = (NodeList) xPath.compile("bounds/bound")
					.evaluate(xml, XPathConstants.NODESET);
			List<SolutionBound<ArraySolution<NumericValue>>> list = new LinkedList<SolutionBound<ArraySolution<NumericValue>>>();
			for (int i = 0; i < bounds.getLength(); i++) {
				Element bound = (Element) bounds.item(i);

				String minBound = (String) xPath.compile("@min").evaluate(
						bound, XPathConstants.STRING);
				Double minValue = null;
				if (minBound != null)
					minValue = Double.parseDouble(minBound);

				String maxBound = (String) xPath.compile("@max").evaluate(
						bound, XPathConstants.STRING);
				Double maxValue = null;
				if (maxBound != null)
					maxValue = Double.parseDouble(maxBound);

				ArrayIndividualBound item = new ArrayIndividualBound(i,
						new Bound(new NumericValue(minValue), new NumericValue(
								maxValue)));
				list.add(item);
			}
			space.setSolutionBounds(list);
			return space;
		}
		return null;
	}

	public Node saveConfiguration(Object instance) {
		if (instance instanceof ArraySolutionSpace) {
			ArraySolutionSpace space = (ArraySolutionSpace) instance;
			Document document = Utils.createDocument();
			Element root = document.createElement("solution_space");
			root.setAttribute("model",
					Config.getModelByModel(ArraySolutionSpaceModel.class).name);
			root.setAttribute("size", String.valueOf(space.getDimension()));

			Element bounds = document.createElement("bounds");
			root.appendChild(bounds);
			if (space.getSolutionBounds() != null
					&& space.getSolutionBounds().size() > 0) {
				for (SolutionBound<ArraySolution<NumericValue>> item : space
						.getSolutionBounds()) {
					Element bound = document.createElement("bound");
					bounds.appendChild(bound);
					ArrayIndividualBound boundItem = (ArrayIndividualBound) item;
					bound.setAttribute(
							"min",
							String.valueOf(boundItem.getBound().getMin()
									.getDouble()));
					bound.setAttribute(
							"max",
							String.valueOf(boundItem.getBound().getMax()
									.getDouble()));
				}
			}
			return root;

		}
		return null;
	}
}
