package org.evolution.population.individual;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.evolution.value.NumericValue;

public class ArrayIndividual<T extends NumericValue> extends Individual {
	private static final NumberFormat formatter = new DecimalFormat("#0.00");
	private List<T> array;

	public ArrayIndividual() {
		this.array = new ArrayList<T>(1);
	}

	public ArrayIndividual(int size) {
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

	public Individual clone() {
		ArrayIndividual<T> clone = new ArrayIndividual<T>();
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
