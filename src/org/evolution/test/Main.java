package org.evolution.test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.evolution.config.AlgorithmFactory;
import org.evolution.criteria.GenerationCriteria;
import org.evolution.exception.InitializeException;
import org.evolution.function.MutateFunction;
import org.evolution.function.cross.OnePointCrossFunction;
import org.evolution.function.fitness.TestFunction;
import org.evolution.function.mutate.StandardMutateFunction;
import org.evolution.function.select.RouleteWheelSelectFunction;
import org.evolution.ga.GeneticAlgorithm;
import org.evolution.population.Population;
import org.evolution.population.solution.ArraySolution;
import org.evolution.population.solution.Solution;
import org.evolution.space.ArraySolutionSpace;
import org.evolution.space.bounds.ArrayIndividualBound;
import org.evolution.space.bounds.SolutionBound;
import org.evolution.util.Bound;
import org.evolution.value.NumericValue;

public class Main {

	private Random rand = new Random();
	private GeneticAlgorithm<ArraySolution<NumericValue>> alg = new GeneticAlgorithm<ArraySolution<NumericValue>>();
	private static final Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		// ConfigModel model = Config.getModel("GeneticAlgorithm");

		Main app = new Main();
		Population pop = new Population(10);
		for (int i = 0; i < pop.getMaxIndividuals(); i++)
			pop.addIndividual(app.createIndividual(2));
		app.getAlgorithm().setPopulation(pop);
		app.getAlgorithm().setFitnessFunction(new TestFunction());
		app.getAlgorithm().setTargetGeneration(10);
		app.getAlgorithm().setTerminateCriteria(
				new GenerationCriteria(app.getAlgorithm()));
		ArraySolutionSpace space = new ArraySolutionSpace(2);
		List<SolutionBound<ArraySolution<NumericValue>>> list = new LinkedList<SolutionBound<ArraySolution<NumericValue>>>();
		list.add(new ArrayIndividualBound(0, new Bound(new NumericValue(10.0),
				new NumericValue(20.0))));
		list.add(new ArrayIndividualBound(1, new Bound(new NumericValue(0.0),
				new NumericValue(10.0))));
		space.setSolutionBounds(list);
		app.getAlgorithm().setSolutionSpace(space);
		app.getAlgorithm().getPopulation()
				.checkBestIndividual(app.getAlgorithm().getFitnessFunction());
		RouleteWheelSelectFunction select = new RouleteWheelSelectFunction();
		select.setProbability(0.8);
		select.setMaxSelected(10);
		app.getAlgorithm().setSelectFunction(select);
		app.getAlgorithm().setCrossFunction(
				new OnePointCrossFunction<ArraySolution<NumericValue>>());
		MutateFunction<ArraySolution<NumericValue>> mutate = new StandardMutateFunction<ArraySolution<NumericValue>>();
		mutate.setProbability(0.2);
		app.getAlgorithm().setMutateFunction(mutate);

		// AlgorithmFactory.save(app.getAlgorithm(), new
		// File("konfigurace.xml"));

		GeneticAlgorithm<ArraySolution<NumericValue>> alg = (GeneticAlgorithm<ArraySolution<NumericValue>>) AlgorithmFactory
				.load(new File("konfigurace.xml"));

		log.debug(alg.getCrossFunction());
		log.debug(alg.getMutateFunction());
		log.debug(alg.getSelectFunction());
		log.debug(alg.getSolutionSpace());
		log.debug(alg.getFitnessFunction());

		try {
			alg.startEvolution();
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			log.error(e);
		} catch (InitializeException e) {
			log.error(e);
		}
		alg.stopEvolution();

	}

	public GeneticAlgorithm<ArraySolution<NumericValue>> getAlgorithm() {
		return alg;
	}

	public Solution createIndividual(int size) {
		ArraySolution<NumericValue> individual = new ArraySolution<NumericValue>();
		for (int i = 0; i < size; i++)
			individual.addValue(new NumericValue(rand.nextDouble()));
		return individual;
	}
}
