package org.evolution.function;

import java.util.List;

import org.evolution.EvolAlgorithm;
import org.evolution.exception.CrossException;
import org.evolution.population.solution.Solution;

public interface CrossFunction<T extends Solution> {

	public List<Solution> cross(List<Solution> individuals)
			throws CrossException;

	public List<Solution> cross(List<Solution> individuals, double probability)
			throws CrossException;

	public void setProbability(double probability);

	public double getProbability();

	public void setAlgorithm(EvolAlgorithm<T> algorithm);

	public EvolAlgorithm<T> getAlgorithm();
}
