package org.evolution.population;

import java.util.Comparator;

import org.evolution.population.individual.Individual;

public class FitnessComparator implements Comparator<Individual> {
	public int compare(Individual ind1, Individual ind2) {
		double fit1 = ind1.getFitness();
		double fit2 = ind2.getFitness();
		if (fit1 > fit2)
			return -1;
		else if (fit1 < fit2)
			return 1;
		else
			return 0;
	}
}
