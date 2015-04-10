package org.evolution.ga;

import java.util.List;

import org.apache.log4j.Logger;
import org.evolution.EvolAlgorithm;
import org.evolution.exception.InitializeException;
import org.evolution.function.CrossFunction;
import org.evolution.function.MutateFunction;
import org.evolution.function.SelectFunction;
import org.evolution.function.cross.OnePointCrossFunction;
import org.evolution.function.mutate.StandardMutateFunction;
import org.evolution.function.select.RouleteWheelSelectFunction;
import org.evolution.population.Population;
import org.evolution.population.individual.Individual;

public class GeneticAlgorithm<T extends Individual> extends EvolAlgorithm<T> {

	private static final Logger log = Logger.getLogger(GeneticAlgorithm.class);

	private SelectFunction selectFunction;
	private CrossFunction<T> crossFunction = new OnePointCrossFunction<T>();
	private MutateFunction<T> mutateFunction;

	public GeneticAlgorithm() {
		setModel("GeneticAlgorithmModel");
	}

	@Override
	public void initialize() throws InitializeException {
		super.initialize();

		MutateFunction<T> mutate = new StandardMutateFunction<T>();
		mutate.setProbability(0.3);
		setMutateFunction(mutate);

		SelectFunction select = new RouleteWheelSelectFunction();
		select.setProbability(1.0);
		setSelectFunction(select);

		setModel("GeneticAlgorithmModel");
	}

	public void run() {
		try {
			getFitnessFunction().evaluate(getPopulation());
			while (!getTerminateCriteria().isTerminated() && isRunning()) {

				List<Individual> list = getPopulation();
				log.debug("POPULATION:" + list.size());
				// SELECT OPERATOR
				List<Individual> selected = getSelectFunction().select(
						getPopulation());
				log.debug("SELECTED:" + selected.size());

				// if it isn´t even number of individual, remove one
				if (selected.size() % 2 != 0) {
					selected.remove(0);
				}
				log.debug("SELECTED:" + selected.size());

				// CROSS OPERATOR
				List<Individual> crossed = getCrossFunction().cross(selected);
				getMutateFunction().mutate(crossed);
				log.debug("MUTATED:" + crossed.size());

				// EVALUATE MUTATED POPULATION
				for (Individual individual : crossed) {
					getFitnessFunction().evaluate(individual);
					log.debug(individual);
				}

				// CREATE NEW POPULATION
				Population newPopulation = new Population(getPopulation()
						.getMaxIndividuals());
				// newPopulation.addAll(getPopulation());
				newPopulation.addAll(crossed);

				// SORTING FROM BEST TO WORST
				newPopulation.sort();
				log.debug("SORTED");
				newPopulation.fitToMaxSize();

				// Set NEW POPULATION
				setPopulation(newPopulation);
				log.debug("SET NEW POPULATION:" + newPopulation.size());
				Individual bestInPopulation = getPopulation()
						.getBestIndividual();
				Individual bestOfAll = getBestSolution();
				log.debug("BEST INDIVIDUAL: " + bestOfAll);
				if (bestInPopulation.getFitness() > bestOfAll.getFitness()) {
					setBestSolution(bestInPopulation);
					log.debug("NEW BEST INDIVIDUAL:" + getBestSolution());
				}

				incrementGeneration();
			}
		} catch (Exception e) {
			log.error(e);
		}
		stopEvolution();
	}

	public SelectFunction getSelectFunction() {
		return selectFunction;
	}

	public void setSelectFunction(SelectFunction selectFunction) {
		this.selectFunction = selectFunction;
	}

	public CrossFunction<T> getCrossFunction() {
		return crossFunction;
	}

	public void setCrossFunction(CrossFunction<T> crossFunction) {
		this.crossFunction = crossFunction;
		this.crossFunction.setAlgorithm(this);
	}

	public MutateFunction<T> getMutateFunction() {
		return mutateFunction;
	}

	public void setMutateFunction(MutateFunction<T> mutateFunction) {
		this.mutateFunction = mutateFunction;
		this.mutateFunction.setAlgorithm(this);
	}
}
