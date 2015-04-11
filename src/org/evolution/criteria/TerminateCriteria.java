package org.evolution.criteria;

import org.evolution.EvolAlgorithm;

public abstract class TerminateCriteria {

	private EvolAlgorithm<?> algorithm = null;

	public TerminateCriteria(EvolAlgorithm<?> algorithm) {
		this.algorithm = algorithm;
	}

	public abstract boolean isTerminated();

	public EvolAlgorithm<?> getAlgorithm() {
		return algorithm;
	}
}
