package org.evolution.exception;

import org.evolution.EvolAlgorithm;

public class InitializeException extends EvolutionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -907054594206323672L;

	public InitializeException() {
		super();
	}

	public InitializeException(String message) {
		super(message);
	}

	public InitializeException(EvolAlgorithm<?> algorithm) {
		super(algorithm);
	}

	public InitializeException(EvolAlgorithm<?> algorithm, String message) {
		super(algorithm, message);
	}
}
