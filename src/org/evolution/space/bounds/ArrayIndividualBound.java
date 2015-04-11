package org.evolution.space.bounds;

import org.evolution.population.solution.ArraySolution;
import org.evolution.util.Bound;
import org.evolution.value.NumericValue;

public class ArrayIndividualBound extends
		SolutionBound<ArraySolution<NumericValue>> {

	private Bound bound;
	private int index;

	public ArrayIndividualBound(int index, Bound bound) {
		this.bound = bound;
		this.index = index;
	}

	public Bound getBound() {
		return bound;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public boolean isValid(ArraySolution<NumericValue> individual) {
		NumericValue value = individual.getValue(index);
		return bound.isInRange(value);
	}

}
