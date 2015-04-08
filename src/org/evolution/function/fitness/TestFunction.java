package org.evolution.function.fitness;

import org.evolution.function.FitnessFunction;
import org.evolution.population.Population;
import org.evolution.population.individual.ArrayIndividual;
import org.evolution.population.individual.Individual;
import org.evolution.value.NumericValue;

public class TestFunction implements FitnessFunction {
	public Double evaluate(Individual individual) {
		if (individual instanceof ArrayIndividual<?>) {
			ArrayIndividual<NumericValue> individualInstance = (ArrayIndividual<NumericValue>) individual;
			Double val = individualInstance.getValue(0).getDouble()
					* individualInstance.getValue(1).getDouble();
			individualInstance.setFitness(val);
			return val;
		}
		return null;
	}

	public void evaluate(Population population) {
		Double maxFitness = null;
		Individual best = null;
		for (Individual indiv : population) {
			Double fitness = evaluate(indiv);
			if (maxFitness == null) {
				maxFitness = fitness;
				best = indiv;
			} else if (maxFitness < fitness) {
				maxFitness = fitness;
				best = indiv;
			}
		}
		population.setBestIndividual(best);
	}
}
