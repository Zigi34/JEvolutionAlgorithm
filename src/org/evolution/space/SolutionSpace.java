package org.evolution.space;

import java.util.List;

import org.evolution.population.individual.Individual;
import org.evolution.space.bounds.SolutionBound;

public interface SolutionSpace<T extends Individual> {

	public abstract Individual randomIndividual();

	public abstract boolean isValid(Individual individual);

	public abstract void repairIndividual(Individual individual);

	public void setSolutionBounds(List<SolutionBound<T>> bounds);

	public List<SolutionBound<T>> getSolutionBounds();
}
