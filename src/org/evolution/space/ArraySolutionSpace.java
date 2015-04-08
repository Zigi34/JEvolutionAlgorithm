package org.evolution.space;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.evolution.population.individual.ArrayIndividual;
import org.evolution.population.individual.Individual;
import org.evolution.space.bounds.ArrayIndividualBound;
import org.evolution.space.bounds.SolutionBound;
import org.evolution.util.Bound;
import org.evolution.value.NumericValue;

public class ArraySolutionSpace implements
		SolutionSpace<ArrayIndividual<NumericValue>> {

	private Hashtable<Integer, ArrayIndividualBound> bounds = new Hashtable<Integer, ArrayIndividualBound>();
	private int dimension;

	public ArraySolutionSpace(int dimension) {
		this.dimension = dimension;
	}

	public Individual randomIndividual() {
		ArrayIndividual<NumericValue> randomIndividual = new ArrayIndividual<NumericValue>(
				dimension);
		for (int i = 0; i < dimension; i++) {
			Bound bound = getBound(i).getBound();
			NumericValue min = bound.getMin();
			NumericValue max = bound.getMax();
			randomIndividual.addValue(NumericValue.randomValue(min, max));
		}
		return randomIndividual;
	}

	public boolean isValid(Individual individual) {
		if (individual instanceof ArrayIndividual<?>) {
			ArrayIndividual<NumericValue> value = (ArrayIndividual<NumericValue>) individual;
			for (SolutionBound<ArrayIndividual<NumericValue>> bound : bounds
					.values()) {
				if (!bound.isValid(value))
					return false;
			}
			return true;
		}
		return false;
	}

	public void repairIndividual(Individual individual) {

	}

	public void setSolutionBounds(
			List<SolutionBound<ArrayIndividual<NumericValue>>> bounds) {
		this.bounds.clear();
		for (SolutionBound<ArrayIndividual<NumericValue>> bound : bounds) {
			ArrayIndividualBound value = (ArrayIndividualBound) bound;
			this.bounds.put(value.getIndex(), value);
		}
	}

	public List<SolutionBound<ArrayIndividual<NumericValue>>> getSolutionBounds() {
		List<SolutionBound<ArrayIndividual<NumericValue>>> list = new LinkedList<SolutionBound<ArrayIndividual<NumericValue>>>();
		list.addAll(this.bounds.values());
		return list;
	}

	private ArrayIndividualBound getBound(int index) {
		return bounds.get(index);
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
}
