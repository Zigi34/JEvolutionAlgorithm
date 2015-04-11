package org.evolution.population.solution;

public abstract class Solution {

	protected double fitness;

	/**
	 * Set actual value of fitness
	 * 
	 * @param fitness
	 *            fitness value
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	/**
	 * Do not actualize fitness function, but only return actual value of
	 * fitness
	 */
	public double getFitness() {
		return fitness;
	}

	public abstract void setValue(Object value, int index);

	public abstract Object getValue(int index);

	public abstract int size();

	public abstract Solution clone();
}
