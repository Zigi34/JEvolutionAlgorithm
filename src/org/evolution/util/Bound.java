package org.evolution.util;

import org.evolution.value.NumericValue;

public class Bound {
	private NumericValue min;
	private NumericValue max;

	public Bound(NumericValue min, NumericValue max) {
		this.min = min;
		this.max = max;
	}

	public NumericValue getMin() {
		return min;
	}

	public void setMin(NumericValue min) {
		this.min = min;
	}

	public NumericValue getMax() {
		return max;
	}

	public void setMax(NumericValue max) {
		this.max = max;
	}

	public boolean isInRange(NumericValue value) {
		if (NumericValue.isGreaterEqual(value, min)
				&& NumericValue.isLowerEqual(value, max))
			return true;
		return false;
	}
}
