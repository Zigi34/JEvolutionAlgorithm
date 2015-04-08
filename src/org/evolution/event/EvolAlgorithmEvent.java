package org.evolution.event;

public class EvolAlgorithmEvent {

	public static final String EVENT_ALGORITHM_START = "start";
	public static final String EVENT_ALGORITHM_STOP = "stop";

	private String name;

	private EvolAlgorithmEvent() {
		this("");
	}

	private EvolAlgorithmEvent(String name) {
		this.name = name;
	}

	public static EvolAlgorithmEvent create(String event) {
		EvolAlgorithmEvent algorithmEvent = new EvolAlgorithmEvent(event);
		return algorithmEvent;
	}

	public String getName() {
		return name;
	}
}
