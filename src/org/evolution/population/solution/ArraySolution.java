package org.evolution.population.solution;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.evolution.value.NumericValue;

public class ArraySolution<T extends NumericValue> extends Solution {
	private static final NumberFormat formatter = new DecimalFormat("#0.00");
	private List<T> array;

	public ArraySolution() {
		this.array = new ArrayList<T>(1);
	}

	public ArraySolution(int size) {
		this.array = new ArrayList<T>(size);
	}

	public void addValue(T value) {
		array.add(value);
	}

	public void removeValue(T value) {
		array.remove(value);
	}

	public void clear() {
		array.clear();
	}

	public T getValue(int index) {
		return array.get(index);
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public double getFitness() {
		return fitness;
	}

	@SuppressWarnings("unchecked")
	public void setValue(Object value, int index) {
		if (value != null && value instanceof Number) {
			T element = (T) value;
			array.set(index, element);
		}
	}

	public int size() {
		return array.size();
	}

	public List<T> getAll() {
		return array;
	}

	public Solution clone() {
		ArraySolution<T> clone = new ArraySolution<T>();
		for (int i = 0; i < size(); i++) {
			NumericValue value = (NumericValue) array.get(i);
			clone.addValue((T) value.clone());
		}
		return clone;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size() - 1; i++) {
			sb.append(formatter.format(getValue(i).getDouble()) + ",");
		}
		sb.append(formatter.format(getValue(size() - 1).getDouble()));
		return String.format("Solution[%s](%s)", getFitness(), sb.toString());
	}
}
