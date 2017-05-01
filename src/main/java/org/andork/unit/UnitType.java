package org.andork.unit;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public abstract class UnitType<T extends UnitType<T>> {
	private final Set<Unit<T>> units = new LinkedHashSet<>();
	private final Set<Unit<T>> unmodifiableUnits = Collections.unmodifiableSet(units);
	
	static final Map<Map<UnitType<?>, Integer>, UnitType<?>> instances = new HashMap<>();
	private final Map<UnitType<?>, UnitType<?>> multipliedBy = new HashMap<>();
	private final Map<UnitType<?>, UnitType<?>> dividedBy = new HashMap<>();
	
	static {
		instances.put(Collections.emptyMap(), Unitless.type);
	}
	
	public UnitType() {
		instances.put(this.getComponents(), this);
	}

	protected final void addUnit(Unit<T> unit) {
		units.add(unit);
	}

	public abstract double convert(double d, Unit<T> from, Unit<T> to);

	public float convertf(float f, Unit<T> from, Unit<T> to) {
		return (float) convert(f, from, to);
	}

	public final Set<Unit<T>> units() {
		return unmodifiableUnits;
	}

	public UnitType<?> mul(UnitType<?> other) {
		UnitType<?> result = multipliedBy.get(other);
		if (result == null) {
			multipliedBy.put(other, result = combine(this, other, 1));
		}
		return result;
	}

	public UnitType<?> divide(UnitType<?> other) {
		UnitType<?> result = dividedBy.get(other);
		if (result == null) {
			dividedBy.put(other, result = combine(this, other, -1));
		}
		return result;
	}
	
	public Map<UnitType<?>, Integer> getComponents() {
		return Collections.singletonMap(this, 1);
	}

	static UnitType<?> combine(UnitType<?> base, UnitType<?> other, int otherPower) {
		Map<UnitType<?>, Integer> components = new HashMap<>(base.getComponents());
		for (Map.Entry<UnitType<?>, Integer> entry : other.getComponents().entrySet()) {
			Integer value = components.get(entry.getKey());
			value = value == null ? entry.getValue() : value + entry.getValue();
			if (value == 0) components.remove(entry.getKey());
			else components.put(entry.getKey(), value);
		}
		return getInstance(components);
	}

	static UnitType<?> getInstance(Map<UnitType<?>, Integer> components) {
		UnitType<?> result = instances.get(components);
		if (result != null) {
		return result;
		}
		if (components.size() == 1) {
			Map.Entry<UnitType<?>, Integer> entry = components.entrySet().iterator().next();
			if (entry.getValue() == 1) {
				return entry.getKey();
			}
		}
		return new CompositeUnitType(components);
	}
}
