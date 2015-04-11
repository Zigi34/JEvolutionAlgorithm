package org.evolution.function.mutate;

import java.util.List;

import org.evolution.EvolAlgorithm;
import org.evolution.exception.MutateException;
import org.evolution.function.MutateFunction;
import org.evolution.population.solution.Solution;
import org.evolution.space.SolutionSpace;
import org.evolution.util.Utils;

public class StandardMutateFunction<T extends Solution> implements
		MutateFunction<T> {

	private double probability;
	private EvolAlgorithm<T> algorithm;

	public void mutate(List<Solution> individuals) throws MutateException {
		mutate(individuals, probability);
	}

	public void mutate(List<Solution> individuals, double probability)
			throws MutateException {
		for (int i = 0; i < individuals.size(); i++) {
			if (Utils.getRandom() < probability) {
				Solution solution = individuals.get(i);
				SolutionSpace<T> space = getAlgorithm().getSolutionSpace();
				Solution randomIndividual = space.randomIndividual();
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

	@Override
	public String toString() {
		return "StandardMutateFunction(" + getProbability() + ")";
	}
}
