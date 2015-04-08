package org.evolution.exception;

import org.evolution.EvolAlgorithm;

public class MutateException extends EvolutionException {

	public MutateException() {
		super();
	}

	public MutateException(String message) {
		super(message);
	}

	public MutateException(EvolAlgorithm algorithm) {
		super(algorithm);
	}

	public MutateException(EvolAlgorithm algorithm, String message) {
		super(algorithm, message);
	}
}
