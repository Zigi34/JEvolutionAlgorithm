package org.evolution.function.mutate;

import java.util.List;

import org.evolution.EvolAlgorithm;
import org.evolution.exception.MutateException;
import org.evolution.function.MutateFunction;
import org.evolution.population.individual.Individual;
import org.evolution.space.SolutionSpace;
import org.evolution.util.Utils;

public class GenericMutateFunction<T extends Individual> implements
		MutateFunction<T> {

	private double probability;
	private EvolAlgorithm<T> algorithm;

	public void mutate(List<Individual> individuals) throws MutateException {
		mutate(individuals, probability);
	}

	public void mutate(List<Individual> individuals, double probability)
			throws MutateException {
		for (int i = 0; i < individuals.size(); i++) {
			if (Utils.getRandom() < probability) {
				Individual solution = individuals.get(i);
				SolutionSpace<T> space = getAlgorithm().getSolutionSpace();
				Individual randomIndividual = space.randomIndividual();
				int randomIndex = Utils.getRandomInt(solution.size());
				solution.setValue(randomIndividual.getValue(randomIndex),
						randomIndex);
			}
		}
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public double getProbability() {
		return probability;
	}

	public void setAlgorithm(EvolAlgorithm<T> algorithm) {
		this.algorithm = algorithm;
	}

	public EvolAlgorithm<T> getAlgorithm() {
		return algorithm;
	}

}
