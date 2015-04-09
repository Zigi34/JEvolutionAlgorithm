package org.evolution;

import java.util.Observable;

import org.apache.log4j.Logger;
import org.evolution.criteria.TerminateCriteria;
import org.evolution.event.EvolAlgorithmEvent;
import org.evolution.exception.InitializeException;
import org.evolution.function.FitnessFunction;
import org.evolution.population.Population;
import org.evolution.population.individual.Individual;
import org.evolution.space.SolutionSpace;
import org.evolution.util.Constants;
import org.evolution.util.PropertyManager;
import org.evolution.util.Utils;

public abstract class EvolAlgorithm<T extends Individual> extends Observable
		implements Runnable {
	// LOGGING
	private static Logger log = Logger.getLogger(EvolAlgorithm.class);

	// THREAD SETTINGS
	private Thread evolThread = null;
	private boolean isRunning = false;

	// POPULATION
	private Population population;

	// TERMINATE CRITERIA
	private TerminateCriteria<T> terminate;

	// FUNCTIONS
	private FitnessFunction fitnessFunction;

	// SOLUTIONS
	Individual bestSolution;

	// SOLUTION SPACE
	SolutionSpace<T> solutionSpace;

	/**
	 * Actual number of generation
	 */
	private int generation = 0;
	private int generationTarget = Utils.toInteger(PropertyManager.getValue(
			Constants.EVOLUTION_CONFIG_FILE,
			Constants.PROPERTY_EVOLUTION_GENERATION_DEFAULT));

	private static final int GENERATION_MIN = Utils.toInteger(PropertyManager
			.getValue(Constants.EVOLUTION_CONFIG_FILE,
					Constants.PROPERTY_EVOLUTION_GENERATION_MIN));
	private static final int GENERATION_MAX = Utils.toInteger(PropertyManager
			.getValue(Constants.EVOLUTION_CONFIG_FILE,
					Constants.PROPERTY_EVOLUTION_GENERATION_MAX));

	public EvolAlgorithm() {

	}

	/**
	 * Inicialize setting for generic evolution algorithm
	 * 
	 * @throws InitializeException
	 */
	public void initialize() throws InitializeException {
		// GENERATION NUMBER SET TO ZERO
		this.generation = 0;

		// TERMINATE PREVIOUS THREAD
		if (this.evolThread != null) {
			Utils.killThread(this.evolThread, Utils.toLong(PropertyManager
					.getValue(Constants.EVOLUTION_CONFIG_FILE,
							Constants.PROPERTY_EVOLUTION_THREAD_WAITTOKILL)));
		}

		// CREATE NEW THREAD
		this.isRunning = true;
		this.evolThread = new Thread(this);

		// CHECK SOLUTION SPACE INITIALIZATION
		if (this.solutionSpace == null) {
			throw new InitializeException(this,
					Constants.EXCEPTION_NULL_SOLUTION_SPACE);
		}

		// SELECT BEST INDIVIDUAL FROM INITIALIZE POPULATION
		getPopulation().checkBestIndividual(getFitnessFunction());
		setBestSolution(getPopulation().getBestIndividual());

		log.debug("Evolution initialized");
	}

	public void startEvolution() throws InitializeException {
		initialize();
		evolThread.start();
		notifyChanged(EvolAlgorithmEvent.EVENT_ALGORITHM_START);
		log.info("Evolution started");
	}

	public void stopEvolution() {
		this.isRunning = false;
		log.info("Stopping evolution...");
	}

	/**
	 * Notify all observers it is changed state of this object
	 * 
	 * @param eventName
	 *            type of event
	 */
	private void notifyChanged(String eventName) {
		setChanged();
		notifyObservers(EvolAlgorithmEvent.create(eventName));
	}

	public Population getPopulation() {
		return population;
	}

	public void setPopulation(Population population) {
		this.population = population;
	}

	public TerminateCriteria<T> getTerminateCriteria() {
		return terminate;
	}

	public void setTerminateCriteria(TerminateCriteria<T> terminate) {
		this.terminate = terminate;
	}

	public int getActualGeneration() {
		return generation;
	}

	public int getTargetGeneration() {
		return generationTarget;
	}

	/**
	 * Set valid target generation for evolution
	 * 
	 * @param generationTarget
	 */
	public void setTargetGeneration(int generationTarget) {
		if (generationTarget >= GENERATION_MIN
				&& generationTarget <= GENERATION_MAX) {
			this.generationTarget = generationTarget;
		} else {
			log.warn(String
					.format("GENERATION TARGET for value %s is not in valid range (%s-%s) ",
							generationTarget, GENERATION_MIN, GENERATION_MAX));
		}

	}

	public FitnessFunction getFitnessFunction() {
		return fitnessFunction;
	}

	public void setFitnessFunction(FitnessFunction fitnessFunction) {
		this.fitnessFunction = fitnessFunction;
	}

	public SolutionSpace<T> getSolutionSpace() {
		return solutionSpace;
	}

	public void setSolutionSpace(SolutionSpace<T> solutionSpace) {
		this.solutionSpace = solutionSpace;
	}

	public Individual getBestSolution() {
		return bestSolution;
	}

	public void setBestSolution(Individual bestSolution) {
		this.bestSolution = bestSolution;
		log.info("New best solution (" + bestSolution + ")");
	}

	public boolean isRunning() {
		return isRunning;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public void incrementGeneration() {
		this.generation++;
	}
}
