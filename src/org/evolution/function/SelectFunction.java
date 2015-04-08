package org.evolution.function;

import java.util.List;

import org.evolution.population.Population;
import org.evolution.population.individual.Individual;

public interface SelectFunction {

	public List<Individual> select(Population population);

	public void setProbability(double probability);

	public double getProbability();

	public void setMaxSelected(int maxSelected);

	public int getMaxSelected();
}
