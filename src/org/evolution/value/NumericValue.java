package org.evolution.value;

import org.evolution.util.Utils;

public class NumericValue {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1498711267980647457L;
	private Number number;

	public NumericValue() {

	}

	public NumericValue(double value) {
		number = value;
	}

	public NumericValue(int value) {
		number = value;
	}

	public NumericValue(float value) {
		number = value;
	}

	public NumericValue(long value) {
		number = value;
	}

	public double getDouble() {
		return number.doubleValue();
	}

	public float getFloat() {
		return number.floatValue();
	}

	public int getInteger() {
		return number.intValue();
	}

	public long getLong() {
		return number.longValue();
	}

	public void setDouble(double number) {
		this.number = number;
	}

	public void setInteger(int number) {
		this.number = number;
	}

	public void setFloat(float number) {
		this.number = number;
	}

	public void setLong(long number) {
		this.number = number;
	}

	public static boolean isGreater(NumericValue value1, NumericValue value2) {
		return value1.getDouble() > value2.getDouble();
	}

	public static boolean isGreaterEqual(NumericValue value1,
			NumericValue value2) {
		return value1.getDouble() >= value2.getDouble();
	}

	public static boolean isLower(NumericValue value1, NumericValue value2) {
		return value1.getDouble() < value2.getDouble();
	}

	public static boolean isLowerEqual(NumericValue value1, NumericValue value2) {
		return value1.getDouble() <= value2.getDouble();
	}

	public static NumericValue randomValue(NumericValue min, NumericValue max) {
		return new NumericValue(Utils.getRandom(min.getDouble(),
				max.getDouble()));
	}

	public NumericValue clone() {
		NumericValue val = new NumericValue();
		if (number instanceof Double)
			val.setDouble(number.doubleValue());
		else if (number instanceof Float)
			val.setFloat(number.floatValue());
		else if (number instanceof Integer)
			val.setInteger(number.intValue());
		else if (number instanceof Long)
			val.setLong(number.longValue());
		return val;
	}
}
