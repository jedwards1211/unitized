package org.andork.unit;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Map;

public class Unit<T extends UnitType<T>> {
	public final T type;
	public final String id;
	private final BigDecimal fromBaseFactor;
	private final BigDecimal toBaseFactor;

	public Unit(T type, String id, BigDecimal fromBaseFactor, BigDecimal toBaseFactor) {
		super();
		this.type = type;
		this.id = id;
		this.fromBaseFactor = fromBaseFactor;
		this.toBaseFactor = toBaseFactor;
	}
	
	public Map<UnitType<?>, Unit<?>> getComponents() {
		return Collections.singletonMap(type, this);
	}
	
	@Override
	public String toString() {
		return id;
	}

	public String toString(UnitizedNumber<T> number) {
		return number.get(this) + " " + id;
	}

	public String toString(UnitizedNumber<T> number, NumberFormat format) {
		return format.format(number.get(this)) + " " + id;
	}
	
	public BigDecimal fromBaseFactor() { return fromBaseFactor; }
	public BigDecimal toBaseFactor() { return toBaseFactor; }
	
	public BigDecimal fromBase(BigDecimal value) {
		return value.multiply(fromBaseFactor());
	}
	
	public BigDecimal toBase(BigDecimal value) {
		return value.multiply(toBaseFactor());
	}
}
