package org.evolution.space;

import java.util.List;

import org.evolution.population.solution.Solution;
import org.evolution.space.bounds.SolutionBound;

public interface SolutionSpace<T extends Solution> {

	public abstract Solution randomIndividual();

	public abstract boolean isValid(Solution individual);

	public abstract void repairIndividual(Solution individual);

	public void setSolutionBounds(List<SolutionBound<T>> bounds);

	public List<SolutionBound<T>> getSolutionBounds();
}
