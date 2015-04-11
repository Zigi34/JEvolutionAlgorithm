package org.evolution.population;

import java.util.Comparator;

import org.evolution.population.solution.Solution;

public class FitnessComparator implements Comparator<Solution> {
	public int compare(Solution ind1, Solution ind2) {
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
