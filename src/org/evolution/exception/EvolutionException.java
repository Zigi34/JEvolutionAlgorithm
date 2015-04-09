package org.evolution.exception;

import org.evolution.EvolAlgorithm;

public abstract class EvolutionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2975295389710818950L;
	private EvolAlgorithm<?> algorithm;

	public EvolutionException() {
		super();
	}

	public EvolutionException(String message) {
		super(message);
	}

	public EvolutionException(EvolAlgorithm<?> algorithm, String message) {
		super(message);
		this.algorithm = algorithm;
	}

	public EvolutionException(EvolAlgorithm<?> algorithm) {
		super();
		this.algorithm = algorithm;
	}

	public EvolAlgorithm<?> getAlgorithm() {
		return algorithm;
	}
}
