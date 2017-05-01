package org.andork.unit;

public class Unitless extends UnitType<Unitless> {
	public static final Unitless type = new Unitless();
	public static final Unit<Unitless> unit = new Unit<Unitless>(type, "", null, null);

	@Override
	public double convert(double d, Unit<Unitless> from, Unit<Unitless> to) {
		if (from != unit) throw new IllegalArgumentException("from is not unitless");
		if (to != unit) throw new IllegalArgumentException("to is not unitless");
		return d;
	}
	
	public static <T extends UnitType<T>> UnitizedDouble<Unitless> divide(UnitizedDouble<T> a, UnitizedDouble<T> b) {
		return new UnitizedDouble<>(a.doubleValue(a.unit) / b.doubleValue(a.unit), unit);
	}
}
