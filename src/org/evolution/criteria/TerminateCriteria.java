package org.evolution.criteria;

import org.evolution.EvolAlgorithm;
import org.evolution.population.individual.Individual;

public abstract class TerminateCriteria<T extends Individual> {

	private EvolAlgorithm<T> algorithm = null;

	public TerminateCriteria(EvolAlgorithm<T> algorithm) {
		this.algorithm = algorithm;
	}

	public abstract boolean isTerminated();

	public EvolAlgorithm<T> getAlgorithm() {
		return algorithm;
	}
}
