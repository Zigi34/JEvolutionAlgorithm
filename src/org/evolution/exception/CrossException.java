package org.evolution.exception;

import org.evolution.EvolAlgorithm;

public class CrossException extends EvolutionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5976256371075113276L;

	public CrossException() {
		super();
	}

	public CrossException(String message) {
		super(message);
	}

	public CrossException(EvolAlgorithm algorithm) {
		super(algorithm);
	}

	public CrossException(EvolAlgorithm algorithm, String message) {
		super(algorithm, message);
	}
}
