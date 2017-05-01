package org.andork.unit;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class UnitType<T extends UnitType<T>> {
	private final Set<Unit<T>> units = new LinkedHashSet<>();
	private final Set<Unit<T>> unmodifiableUnits = Collections.unmodifiableSet(units);

	protected final void addUnit(Unit<T> unit) {
		units.add(unit);
	}

	public double convert(double d, Unit<T> from, Unit<T> to) {
		return from.toBaseFactor().multiply(to.fromBaseFactor()).multiply(new BigDecimal(d)).doubleValue();
	}

	public float convertf(float f, Unit<T> from, Unit<T> to) {
		return (float) convert(f, from, to);
	}

	public final Set<Unit<T>> units() {
		return unmodifiableUnits;
	}
}
