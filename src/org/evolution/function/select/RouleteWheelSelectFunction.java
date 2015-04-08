package org.evolution.function.select;

import java.util.LinkedList;
import java.util.List;

import org.evolution.function.SelectFunction;
import org.evolution.population.Population;
import org.evolution.population.individual.Individual;
import org.evolution.util.Constants;
import org.evolution.util.PropertyManager;
import org.evolution.util.Utils;

public class RouleteWheelSelectFunction implements SelectFunction {

	private double probability = Double.parseDouble(PropertyManager.getValue(
			Constants.EVOLUTION_CONFIG_FILE,
			Constants.PROPERTY_SELECT_DEFAULT_PROBABILITY));
	private Integer maxSelected;

	public List<Individual> select(Population population) {
		List<Double> fitnesses = new LinkedList<Double>();
		double sumValue = 0.0;
		for (Individual item : population) {
			sumValue += item.getFitness();
			fitnesses.add(sumValue);
		}

		List<Individual> result = new LinkedList<Individual>();
		int maxItems = 0;

		if (maxSelected != null)
			maxItems = maxSelected;
		else
			maxItems = (int) Math.round(population.size() * probability);

		for (int index = 0; index < maxItems; index++) {
			double rand = Utils.getRandom() * sumValue;
			int position = 0;
			while (position < fitnesses.size()
					&& fitnesses.get(position) < rand) {
				position++;
			}
			if (position == fitnesses.size())
				position -= 1;
			result.add(population.get(position));
		}
		return result;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public double getProbability() {
		return this.probability;
	}

	public void setMaxSelected(int maxSelected) {
		this.maxSelected = maxSelected;
	}

	public int getMaxSelected() {
		return maxSelected;
	}
}
