package org.evolution.criteria;

import org.evolution.EvolAlgorithm;

public class GenerationCriteria extends TerminateCriteria {

	public GenerationCriteria(EvolAlgorithm algorithm) {
		super(algorithm);
	}

	@Override
	public boolean isTerminated() {
		boolean isOver = (getAlgorithm().getActualGeneration() > getAlgorithm()
				.getTargetGeneration()) ? true : false;
		return isOver;
	}
}
