package org.evolution.function.fitness;

import org.evolution.function.FitnessFunction;
import org.evolution.population.Population;
import org.evolution.population.solution.ArraySolution;
import org.evolution.population.solution.Solution;
import org.evolution.value.NumericValue;

public class TestFunction implements FitnessFunction {
	public Double evaluate(Solution individual) {
		if (individual instanceof ArraySolution<?>) {
			ArraySolution<NumericValue> individualInstance = (ArraySolution<NumericValue>) individual;
			Double val = individualInstance.getValue(0).getDouble()
					* individualInstance.getValue(1).getDouble();
			individualInstance.setFitness(val);
			return val;
		}
		return null;
	}

	public void evaluate(Population population) {
		Double maxFitness = null;
		Solution best = null;
		for (Solution indiv : population) {
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

	@Override
	public String toString() {
		return "TestFitness";
	}
}
