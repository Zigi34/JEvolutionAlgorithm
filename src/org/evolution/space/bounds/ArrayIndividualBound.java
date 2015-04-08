package org.evolution.space.bounds;

import org.evolution.population.individual.ArrayIndividual;
import org.evolution.util.Bound;
import org.evolution.value.NumericValue;

public class ArrayIndividualBound extends
		SolutionBound<ArrayIndividual<NumericValue>> {

	private Bound bound;
	private int index;

	public ArrayIndividualBound() {
	}

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
	public boolean isValid(ArrayIndividual<NumericValue> individual) {
		NumericValue value = individual.getValue(index);
		return bound.isInRange(value);
	}

}
