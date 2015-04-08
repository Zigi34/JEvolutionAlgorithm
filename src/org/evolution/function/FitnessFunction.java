package org.evolution.function;

import org.evolution.population.Population;
import org.evolution.population.individual.Individual;

public interface FitnessFunction {
	public Double evaluate(Individual individual);

	public void evaluate(Population population);
}
