package org.andork.unit;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Length extends UnitType<Length> {
	public static final Length type;

	public static final Unit<Length> kilometers;
	public static final Unit<Length> meters;
	public static final Unit<Length> centimeters;
	public static final Unit<Length> miles;
	public static final Unit<Length> yards;
	public static final Unit<Length> feet;
	public static final Unit<Length> inches;

	private static final Set<Unit<Length>> mutableImperialUnits = new HashSet<>();
	private static final Set<Unit<Length>> mutableMetricUnits = new HashSet<>();

	public static final Set<Unit<Length>> imperialUnits = Collections.unmodifiableSet(mutableImperialUnits);
	public static final Set<Unit<Length>> metricUnits = Collections.unmodifiableSet(mutableMetricUnits);

	static {
		type = new Length();
		type.addUnit(kilometers = new Unit<>(type, "km", new BigDecimal("0.001"), new BigDecimal(1000)));
		type.addUnit(meters = new Unit<>(type, "m", BigDecimal.ONE, BigDecimal.ONE));
		type.addUnit(centimeters = new Unit<>(type, "cm", new BigDecimal(100), new BigDecimal("0.01")));

		type.addUnit(feet = new Unit<>(type, "ft", 
				BigDecimal.ONE.divide(new BigDecimal("0.3048"), 64, BigDecimal.ROUND_HALF_EVEN), 
				new BigDecimal("0.3048")));
		type.addUnit(miles = new Unit<>(type, "mi",
				feet.fromBaseFactor().divide(new BigDecimal(5280), 64, BigDecimal.ROUND_HALF_EVEN),
				feet.toBaseFactor().multiply(new BigDecimal(5280))));
		type.addUnit(yards = new Unit<>(type, "yd",
				feet.fromBaseFactor().divide(new BigDecimal(3), 64, BigDecimal.ROUND_HALF_EVEN),
				feet.toBaseFactor().multiply(new BigDecimal(3))));
		type.addUnit(inches = new Unit<>(type, "in",
				feet.fromBaseFactor().multiply(new BigDecimal(12)),
				feet.toBaseFactor().divide(new BigDecimal(12), 64, BigDecimal.ROUND_HALF_EVEN)));

		mutableMetricUnits.add(kilometers);
		mutableMetricUnits.add(meters);
		mutableMetricUnits.add(centimeters);

		mutableImperialUnits.add(miles);
		mutableImperialUnits.add(yards);
		mutableImperialUnits.add(feet);
		mutableImperialUnits.add(inches);
	}

	private Length() {

	}
	
	public static UnitizedDouble<Length> kilometers(double value) {
		return new UnitizedDouble<>(value, kilometers);
	}
	
	public static UnitizedDouble<Length> meters(double value) {
		return new UnitizedDouble<>(value, meters);
	}
	
	public static UnitizedDouble<Length> centimeters(double value) {
		return new UnitizedDouble<>(value, centimeters);
	}
	
	public static UnitizedDouble<Length> miles(double value) {
		return new UnitizedDouble<>(value, miles);
	}

	public static UnitizedDouble<Length> yards(double value) {
		return new UnitizedDouble<>(value, yards);
	}

	public static UnitizedDouble<Length> feet(double value) {
		return new UnitizedDouble<>(value, feet);
	}

	public static UnitizedDouble<Length> inches(double value) {
		return new UnitizedDouble<>(value, inches);
	}
}
