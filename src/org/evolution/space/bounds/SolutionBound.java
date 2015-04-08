package org.evolution.space.bounds;

import org.evolution.population.individual.Individual;

public abstract class SolutionBound<T extends Individual> {
	public abstract boolean isValid(T individual);
}
