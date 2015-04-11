package org.evolution.space.bounds;

import org.evolution.population.solution.Solution;

public abstract class SolutionBound<T extends Solution> {
	public abstract boolean isValid(T individual);
}
