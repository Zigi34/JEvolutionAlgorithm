package org.evolution.function;

import java.util.List;

import org.evolution.EvolAlgorithm;
import org.evolution.exception.MutateException;
import org.evolution.population.solution.Solution;

public interface MutateFunction<T extends Solution> {

	public void mutate(List<Solution> individuals) throws MutateException;

	public void mutate(List<Solution> individuals, double probability)
			throws MutateException;

	public void setProbability(double probability);

	public double getProbability();

	public void setAlgorithm(EvolAlgorithm<T> algorithm);

	public EvolAlgorithm<T> getAlgorithm();
}
