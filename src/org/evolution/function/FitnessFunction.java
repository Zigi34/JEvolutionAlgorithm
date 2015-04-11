package org.evolution.function;

import org.evolution.population.Population;
import org.evolution.population.solution.Solution;

public interface FitnessFunction {
	public Double evaluate(Solution individual);

	public void evaluate(Population population);
}
