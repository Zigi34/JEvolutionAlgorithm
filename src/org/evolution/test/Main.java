package org.evolution.test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.evolution.config.model.EvolAlgorithmModel;
import org.evolution.criteria.GenerationCriteria;
import org.evolution.exception.InitializeException;
import org.evolution.function.fitness.TestFunction;
import org.evolution.ga.GeneticAlgorithm;
import org.evolution.population.Population;
import org.evolution.population.individual.ArrayIndividual;
import org.evolution.population.individual.Individual;
import org.evolution.space.ArraySolutionSpace;
import org.evolution.space.bounds.ArrayIndividualBound;
import org.evolution.space.bounds.SolutionBound;
import org.evolution.util.Bound;
import org.evolution.util.Config;
import org.evolution.value.NumericValue;

public class Main {

	private Random rand = new Random();
	private GeneticAlgorithm<ArrayIndividual<NumericValue>> alg = new GeneticAlgorithm<ArrayIndividual<NumericValue>>();
	private static final Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		EvolAlgorithmModel model = Config.getAlgorithm("GeneticAlgorithm");

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
		List<SolutionBound<ArrayIndividual<NumericValue>>> list = new LinkedList<SolutionBound<ArrayIndividual<NumericValue>>>();
		list.add(new ArrayIndividualBound(0, new Bound(new NumericValue(10.0),
				new NumericValue(20.0))));
		list.add(new ArrayIndividualBound(1, new Bound(new NumericValue(0.0),
				new NumericValue(10.0))));
		space.setSolutionBounds(list);
		// app.getAlgorithm().setSolutionSpace(space);
		// app.getAlgorithm().setCrossFunction(
		// new OnePointCrossFunction<ArrayIndividual<NumericValue>>());
		// MutateFunction<ArrayIndividual<NumericValue>> mutate = new
		// GenericMutateFunction<ArrayIndividual<NumericValue>>();
		// mutate.setProbability(0.2);
		// app.getAlgorithm().setMutateFunction(mutate);

		try {
			app.getAlgorithm().startEvolution();
			Thread.sleep(2 * 1000);
		} catch (InterruptedException e) {
			log.error(e);
		} catch (InitializeException e) {
			log.error(e);
		}

		app.getAlgorithm().stopEvolution();
	}

	public GeneticAlgorithm<ArrayIndividual<NumericValue>> getAlgorithm() {
		return alg;
	}

	public Individual createIndividual(int size) {
		ArrayIndividual<NumericValue> individual = new ArrayIndividual<NumericValue>();
		for (int i = 0; i < size; i++)
			individual.addValue(new NumericValue(rand.nextDouble()));
		return individual;
	}
}
