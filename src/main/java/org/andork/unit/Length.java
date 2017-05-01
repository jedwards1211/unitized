package org.andork.unit;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

	private static final Set<Unit<Length>> imperialUnits = new HashSet<>();
	private static final Set<Unit<Length>> metricUnits = new HashSet<>();

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

		metricUnits.add(kilometers);
		metricUnits.add(meters);
		metricUnits.add(centimeters);

		imperialUnits.add(miles);
		imperialUnits.add(yards);
		imperialUnits.add(feet);
		imperialUnits.add(inches);
	}

	private Length() {

	}

	@Override
	public double convert(double d, Unit<Length> from, Unit<Length> to) {
		return from.toBaseFactor().multiply(to.fromBaseFactor()).multiply(new BigDecimal(d)).doubleValue();
	}
}
