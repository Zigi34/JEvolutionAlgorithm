package org.evolution.config.algorithm;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.evolution.config.Config;
import org.evolution.config.ConfigModel;
import org.evolution.config.ModelFactory;
import org.evolution.function.CrossFunction;
import org.evolution.function.FitnessFunction;
import org.evolution.function.MutateFunction;
import org.evolution.function.SelectFunction;
import org.evolution.ga.GeneticAlgorithm;
import org.evolution.population.Population;
import org.evolution.population.solution.ArraySolution;
import org.evolution.space.SolutionSpace;
import org.evolution.util.Utils;
import org.evolution.value.NumericValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class GeneticAlgorithmModel implements ModelFactory {

	public Object loadConfiguration(Node xml) throws XPathExpressionException {

		XPath xPath = Utils.getXPath();

		GeneticAlgorithm<?> algorithm = new GeneticAlgorithm();

		// POPULATION
		Node population = (Node) xPath.compile("algorithm/population")
				.evaluate(xml, XPathConstants.NODE);
		if (population != null) {
			String model = population.getAttributes().getNamedItem("model")
					.getTextContent();
			Population pop = (Population) Config.getModelByName(model)
					.createFactory().loadConfiguration(population);
			algorithm.setPopulation(pop);
		}

		// SELECT FUNCTION
		Node select = (Node) xPath.compile("algorithm/select_function")
				.evaluate(xml, XPathConstants.NODE);
		if (select != null) {
			String model = select.getAttributes().getNamedItem("model")
					.getTextContent();
			SelectFunction selectFce = (SelectFunction) Config
					.getModelByName(model).createFactory()
					.loadConfiguration(select);
			algorithm.setSelectFunction(selectFce);
		}

		// CROSS FUNCTION
		Node cross = (Node) xPath.compile("algorithm/cross_function").evaluate(
				xml, XPathConstants.NODE);
		if (cross != null) {
			String model = cross.getAttributes().getNamedItem("model")
					.getTextContent();
			CrossFunction<ArraySolution<NumericValue>> crossFce = (CrossFunction<ArraySolution<NumericValue>>) Config
					.getModelByName(model).createFactory()
					.loadConfiguration(cross);
			algorithm.setCrossFunction(crossFce);
		}

		// MUTATE FUNCTION
		Node mutate = (Node) xPath.compile("algorithm/mutate_function")
				.evaluate(xml, XPathConstants.NODE);
		if (mutate != null) {
			String model = mutate.getAttributes().getNamedItem("model")
					.getTextContent();
			MutateFunction<ArraySolution<NumericValue>> mutateFce = (MutateFunction<ArraySolution<NumericValue>>) Config
					.getModelByName(model).createFactory()
					.loadConfiguration(mutate);
			algorithm.setMutateFunction(mutateFce);
		}

		// SOLUTION SPACE
		Node space = (Node) xPath.compile("algorithm/solution_space").evaluate(
				xml, XPathConstants.NODE);
		if (space != null) {
			String model = space.getAttributes().getNamedItem("model")
					.getTextContent();
			SolutionSpace<ArraySolution<NumericValue>> solutionSpaceFce = (SolutionSpace<ArraySolution<NumericValue>>) Config
					.getModelByName(model).createFactory()
					.loadConfiguration(space);
			algorithm.setSolutionSpace(solutionSpaceFce);
		}

		// FITNESS FUNCTION
		Node fitness = (Node) xPath.compile("algorithm/fitness_function")
				.evaluate(xml, XPathConstants.NODE);
		if (fitness != null) {
			String model = fitness.getAttributes().getNamedItem("model")
					.getTextContent();
			FitnessFunction fitnessFce = (FitnessFunction) Config
					.getModelByName(model).createFactory()
					.loadConfiguration(fitness);
			algorithm.setFitnessFunction(fitnessFce);
		}

		// TARGET GENERATION
		Node generation = (Node) xPath.compile("algorithm/generation")
				.evaluate(xml, XPathConstants.NODE);
		if (generation != null)
			algorithm.setTargetGeneration(Integer.parseInt(generation
					.getTextContent()));

		return algorithm;
	}

	public Node saveConfiguration(Object instance) {
		if (instance instanceof GeneticAlgorithm<?>) {
			GeneticAlgorithm<?> ga = (GeneticAlgorithm<?>) instance;
			Document document = Utils.createDocument();
			Element root = document.createElement("algorithm");
			ConfigModel model = Config
					.getModelByModel(GeneticAlgorithmModel.class);
			root.setAttribute("model", model.name);

			// SET MAX GENERATION
			Element generation = document.createElement("generation");
			generation.setTextContent(String.valueOf(ga.getTargetGeneration()));
			root.appendChild(generation);

			// SELECT FUNCTION
			if (ga.getSelectFunction() != null) {
				ModelFactory factory = Config.getModelByInstance(
						ga.getSelectFunction().getClass()).createFactory();
				root.appendChild(document.importNode(
						factory.saveConfiguration(ga.getSelectFunction()), true));
			}

			// CROSS FUNCTION
			if (ga.getCrossFunction() != null) {
				ModelFactory factory = Config.getModelByInstance(
						ga.getCrossFunction().getClass()).createFactory();
				root.appendChild(document.importNode(
						factory.saveConfiguration(ga.getCrossFunction()), true));
			}

			// MUTATE FUNCTION
			if (ga.getMutateFunction() != null) {
				ModelFactory factory = Config.getModelByInstance(
						ga.getMutateFunction().getClass()).createFactory();
				root.appendChild(document.importNode(
						factory.saveConfiguration(ga.getMutateFunction()), true));
			}

			// POPULATION
			if (ga.getPopulation() != null) {
				ModelFactory factory = Config.getModelByInstance(
						Population.class).createFactory();
				root.appendChild(document.importNode(
						factory.saveConfiguration(ga.getPopulation()), true));
			}

			// SOLUTION SPACE
			if (ga.getSolutionSpace() != null) {
				ModelFactory factory = Config.getModelByInstance(
						ga.getSolutionSpace().getClass()).createFactory();
				root.appendChild(document.importNode(
						factory.saveConfiguration(ga.getSolutionSpace()), true));
			}

			// FITNESS FUNCTION
			if (ga.getFitnessFunction() != null) {
				ModelFactory factory = Config.getModelByInstance(
						ga.getFitnessFunction().getClass()).createFactory();
				root.appendChild(document.importNode(
						factory.saveConfiguration(ga.getFitnessFunction()),
						true));
			}
			return root;
		}
		return null;
	}
}
