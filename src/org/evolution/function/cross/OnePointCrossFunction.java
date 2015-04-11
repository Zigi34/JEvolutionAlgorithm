package org.evolution.function.cross;

import java.util.LinkedList;
import java.util.List;

import org.evolution.EvolAlgorithm;
import org.evolution.exception.CrossException;
import org.evolution.function.CrossFunction;
import org.evolution.population.solution.Solution;
import org.evolution.util.Constants;
import org.evolution.util.Utils;

public class OnePointCrossFunction<T extends Solution> implements
		CrossFunction<T> {

	private EvolAlgorithm<T> algorithm;
	private double probability = 0.75;

	public List<Solution> cross(List<Solution> individuals)
			throws CrossException {
		return cross(individuals, this.probability);
	}

	public List<Solution> cross(List<Solution> individuals, double probability)
			throws CrossException {

		if (individuals.size() % 2 != 0) {
			throw new CrossException(this.algorithm,
					Constants.EXCEPTION_MUST_BE_EVEN_INDIVIDUAL);

		}
		List<Solution> list = new LinkedList<Solution>();
		for (int i = 1; i < individuals.size(); i += 2) {
			Solution item1 = individuals.get(i - 1).clone();
			Solution item2 = individuals.get(i).clone();

			int length = item1.size();
			int index1 = Utils.getRandomInt(length - 1) + 1;

			for (int j = index1; j < length; j++) {
				Object itemObject1 = item1.getValue(j);
				Object itemObject2 = item2.getValue(j);

				item1.setValue(itemObject2, j);
				item2.setValue(itemObject1, j);
			}

			list.add(item1);
			list.add(item2);
		}

		return list;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public double getProbability() {
		return this.probability;
	}

	public void setAlgorithm(EvolAlgorithm<T> algorithm) {
		this.algorithm = algorithm;
	}

	public EvolAlgorithm<T> getAlgorithm() {
		return this.algorithm;
	}

	@Override
	public String toString() {
		return "OnePointCross(" + getProbability() + ")";
	}

}
