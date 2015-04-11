package org.evolution.population;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.evolution.function.FitnessFunction;
import org.evolution.population.solution.Solution;

public class Population implements List<Solution> {
	// LOGGING
	private static final Logger log = Logger.getLogger(Population.class);

	// INDIVIDUALS
	private List<Solution> individuals = new ArrayList<Solution>(20);
	private Solution bestIndividual = null;
	private int maxIndividuals = 0;

	private static final FitnessComparator comparator = new FitnessComparator();

	public Population() {

	}

	public Population(int maxIndividuals) {
		this.maxIndividuals = maxIndividuals;
	}

	/**
	 * Sorting individuals in population from the best fitness to worst
	 */
	public void sort() {
		Collections.sort(individuals, comparator);
		bestIndividual = individuals.get(0);
	}

	/**
	 * Fit size of population to max size property. Objects are removed from
	 * tail
	 */
	public void fitToMaxSize() {
		while (size() > getMaxIndividuals())
			remove(size() - 1);
	}

	public void addIndividual(Solution indiv) {
		if (!individuals.contains(indiv)) {
			individuals.add(indiv);
		}
	}

	public void removeIndividual(Solution indiv) {
		individuals.remove(indiv);
	}

	public void clear() {
		individuals.clear();
	}

	public int getMaxIndividuals() {
		return maxIndividuals;
	}

	public void setMaxIndividuals(int maxIndividuals) {
		this.maxIndividuals = maxIndividuals;
	}

	public void checkBestIndividual(FitnessFunction function) {
		if (individuals.size() > 0) {
			Solution best = individuals.get(0);
			Double bestFitness = function.evaluate(best);
			for (Solution individual : individuals) {
				Double fitness = function.evaluate(individual);
				if (fitness > bestFitness) {
					best = individual;
					bestFitness = fitness;
				}
			}
			this.bestIndividual = best;
		}
	}

	public void checkBestIndividual() {
		checkBestIndividual(null);
	}

	public Solution getBestIndividual() {
		return this.bestIndividual;
	}

	public void setBestIndividual(Solution bestIndividual) {
		this.bestIndividual = bestIndividual;
	}

	public boolean add(Solution individual) {
		return individuals.add(individual);
	}

	public void add(int index, Solution individual) {
		individuals.add(index, individual);
	}

	public boolean addAll(Collection<? extends Solution> list) {
		return individuals.addAll(list);
	}

	public boolean addAll(int index, Collection<? extends Solution> list) {
		return individuals.addAll(index, list);
	}

	public boolean contains(Object value) {
		return individuals.contains(value);
	}

	public boolean containsAll(Collection<?> list) {
		return individuals.containsAll(list);
	}

	public Solution get(int index) {
		return individuals.get(index);
	}

	public int indexOf(Object arg0) {
		return individuals.indexOf(arg0);
	}

	public boolean isEmpty() {
		return individuals.isEmpty();
	}

	public Iterator<Solution> iterator() {
		return individuals.iterator();
	}

	public int lastIndexOf(Object arg0) {
		return individuals.lastIndexOf(arg0);
	}

	public ListIterator<Solution> listIterator() {
		return individuals.listIterator();
	}

	public ListIterator<Solution> listIterator(int arg0) {
		return individuals.listIterator(arg0);
	}

	public boolean remove(Object arg0) {
		return individuals.remove(arg0);
	}

	public Solution remove(int arg0) {
		return individuals.remove(arg0);
	}

	public boolean removeAll(Collection<?> arg0) {
		return individuals.removeAll(arg0);
	}

	public boolean retainAll(Collection<?> arg0) {
		return individuals.retainAll(arg0);
	}

	public Solution set(int arg0, Solution arg1) {
		return individuals.set(arg0, arg1);
	}

	public int size() {
		return individuals.size();
	}

	public List<Solution> subList(int arg0, int arg1) {
		return individuals.subList(arg0, arg1);
	}

	public Object[] toArray() {
		return individuals.toArray();
	}

	public <T> T[] toArray(T[] arg0) {
		return individuals.toArray(arg0);
	}
}
