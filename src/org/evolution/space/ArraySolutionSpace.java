package org.evolution.space;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.evolution.population.solution.ArraySolution;
import org.evolution.population.solution.Solution;
import org.evolution.space.bounds.ArrayIndividualBound;
import org.evolution.space.bounds.SolutionBound;
import org.evolution.util.Bound;
import org.evolution.value.NumericValue;

public class ArraySolutionSpace implements
		SolutionSpace<ArraySolution<NumericValue>> {

	private Hashtable<Integer, ArrayIndividualBound> bounds = new Hashtable<Integer, ArrayIndividualBound>();
	private int dimension;

	public ArraySolutionSpace(int dimension) {
		this.dimension = dimension;
	}

	public Solution randomIndividual() {
		ArraySolution<NumericValue> randomIndividual = new ArraySolution<NumericValue>(
				dimension);
		for (int i = 0; i < dimension; i++) {
			Bound bound = getBound(i).getBound();
			NumericValue min = bound.getMin();
			NumericValue max = bound.getMax();
			randomIndividual.addValue(NumericValue.randomValue(min, max));
		}
		return randomIndividual;
	}

	public boolean isValid(Solution individual) {
		if (individual instanceof ArraySolution<?>) {
			ArraySolution<NumericValue> value = (ArraySolution<NumericValue>) individual;
			for (SolutionBound<ArraySolution<NumericValue>> bound : bounds
					.values()) {
				if (!bound.isValid(value))
					return false;
			}
			return true;
		}
		return false;
	}

	public void repairIndividual(Solution individual) {

	}

	public void setSolutionBounds(
			List<SolutionBound<ArraySolution<NumericValue>>> bounds) {
		this.bounds.clear();
		for (SolutionBound<ArraySolution<NumericValue>> bound : bounds) {
			ArrayIndividualBound value = (ArrayIndividualBound) bound;
			this.bounds.put(value.getIndex(), value);
		}
	}

	public List<SolutionBound<ArraySolution<NumericValue>>> getSolutionBounds() {
		List<SolutionBound<ArraySolution<NumericValue>>> list = new LinkedList<SolutionBound<ArraySolution<NumericValue>>>();
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

	@Override
	public String toString() {
		return "ArraySolutionSpace(" + getDimension() + ")";
	}
}
