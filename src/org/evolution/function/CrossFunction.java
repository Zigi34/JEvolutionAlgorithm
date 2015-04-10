package org.evolution.function;

import java.util.List;

import org.evolution.EvolAlgorithm;
import org.evolution.exception.CrossException;
import org.evolution.population.individual.Individual;

public interface CrossFunction<T extends Individual> {

	public List<Individual> cross(List<Individual> individuals)
			throws CrossException;

	public List<Individual> cross(List<Individual> individuals,
			double probability) throws CrossException;

	public void setProbability(double probability);

	public double getProbability();

	public void setAlgorithm(EvolAlgorithm<T> algorithm);

	public EvolAlgorithm<T> getAlgorithm();

	public String getName();
}
