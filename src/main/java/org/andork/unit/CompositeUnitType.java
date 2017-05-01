package org.andork.unit;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CompositeUnitType extends UnitType<CompositeUnitType> {
	final Map<UnitType<?>, Integer> components;
	
	final Map<Map<UnitType<?>, Unit<?>>, CompositeUnit> units = new HashMap<>();
	
	public Map<UnitType<?>, Integer> getComponents() {
		return components;
	}

	CompositeUnitType(Map<UnitType<?>, Integer> components) {
		super();
		this.components = Collections.unmodifiableMap(components);
	}
	
	public CompositeUnit getUnit(Map<UnitType<?>, Unit<?>> components) {
		CompositeUnit result = units.get(components);
		if (result != null) {
			return result;
		}
		return new CompositeUnit(this, components);
	}
	
	@Override
	public int hashCode() {
		return components.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeUnitType other = (CompositeUnitType) obj;
		if (components == null) {
			if (other.components != null)
				return false;
		} else if (!components.equals(other.components))
			return false;
		return true;
	}
	
	public BigDecimal getConversionFactor(Unit<?> from, Unit<?> to) {
		if (from.type != this) {
			throw new IllegalArgumentException("from has the wrong unit type");
		}
		if (to.type != this) {
			throw new IllegalArgumentException("to has the wrong unit type");
		}
		BigDecimal factor = BigDecimal.ONE;
		if (from == to) return factor;
		
		Map<UnitType<?>, Unit<?>> fromComponents = from.getComponents();
		Map<UnitType<?>, Unit<?>> toComponents = to.getComponents();

		for (Map.Entry<UnitType<?>, Integer> component : components.entrySet()) {
			UnitType<?> unitType = component.getKey();
			int power = component.getValue();
			Unit<?> fromUnit = fromComponents.get(unitType);
			Unit<?> toUnit = toComponents.get(unitType);
			if (fromUnit == toUnit) continue;
			factor = factor.multiply(fromUnit.toBaseFactor().multiply(toUnit.fromBaseFactor()).pow(power));
		}
		return factor;
	}
	
	public BigDecimal getPartialConversionFactor(Unit<?> from, Unit<?> to) {
		BigDecimal factor = BigDecimal.ONE;
		if (from == to) return factor;
		
		Map<UnitType<?>, Unit<?>> fromComponents = from.getComponents();
		Map<UnitType<?>, Unit<?>> toComponents = to.getComponents();

		for (Map.Entry<UnitType<?>, Integer> component : components.entrySet()) {
			UnitType<?> unitType = component.getKey();
			int power = component.getValue();
			Unit<?> fromUnit = fromComponents.get(unitType);
			Unit<?> toUnit = toComponents.get(unitType);
			if (fromUnit == null || toUnit == null || fromUnit == toUnit) continue;
			factor = factor.multiply(fromUnit.toBaseFactor().multiply(toUnit.fromBaseFactor()).pow(power));
		}
		return factor;	
	}

	@Override
	public double convert(double d, Unit<CompositeUnitType> from, Unit<CompositeUnitType> to) {
		return getConversionFactor(from, to).multiply(new BigDecimal(d)).doubleValue();
	}
}
