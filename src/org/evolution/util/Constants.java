package org.evolution.util;

public final class Constants {
	/**
	 * Property soubor, kde jsou uloženy obecné hodnoty pro nastavení programu
	 */
	public static final String CONFIG_FILE = "/config/config.properties";
	public static final String EVOLUTION_CONFIG_FILE = "/config/evolution.properties";

	// Property names for application configure file
	public static final String PROPERTY_EVOLUTION_CONFIG_FILE = "evolution.algorithm.property.path";

	// Property names for evolution configure file
	public static final String PROPERTY_EVOLUTION_GENERATION_MIN = "evolution.generation.min";
	public static final String PROPERTY_EVOLUTION_GENERATION_MAX = "evolution.generation.max";
	public static final String PROPERTY_EVOLUTION_GENERATION_DEFAULT = "evolution.generation.default";
	public static final String PROPERTY_THREAD_WAITTOKILL = "evolution.thread.waitToKill";
	public static final String PROPERTY_SELECT_DEFAULT_PROBABILITY = "evolution.function.select.defaultProbability";

	// Cross exception
	public static final String EXCEPTION_MUST_BE_EVEN_INDIVIDUAL = "It must be even number of individuals for cross operation";
}
