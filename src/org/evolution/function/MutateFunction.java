package org.evolution.function;

import java.util.List;

import org.evolution.EvolAlgorithm;
import org.evolution.exception.MutateException;
import org.evolution.population.individual.Individual;

public interface MutateFunction<T extends Individual> {

	public void mutate(List<Individual> individuals) throws MutateException;

	public void mutate(List<Individual> individuals, double probability)
			throws MutateException;

	public void setProbability(double probability);

	public double getProbability();

	public void setAlgorithm(EvolAlgorithm<T> algorithm);

	public EvolAlgorithm<T> getAlgorithm();
}
