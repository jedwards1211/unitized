package org.andork.unit;

import java.util.Collections;
import java.util.Map;

public class CompositeUnit extends Unit<CompositeUnitType> {
	final Map<UnitType<?>, Unit<?>> components;
	
	public Map<UnitType<?>, Unit<?>> getComponents() {
		return components;
	}

	CompositeUnit(CompositeUnitType type, Map<UnitType<?>, Unit<?>> components) {
		super(type, createId(type, components), null, null);
		this.components = Collections.unmodifiableMap(components);
		type.units.put(components, this);
	}

	private static String createId(CompositeUnitType type, Map<UnitType<?>, Unit<?>> components2) {
		StringBuilder sb = new StringBuilder();
		for (UnitType<?> unitType : type.components.keySet()) {
			if (sb.length() > 0) sb.append(" * ");
			sb.append(components2.get(unitType).id);
			int power = type.components.get(unitType);
			if (power != 1) sb.append("^").append(power);
		}
		return sb.toString();
	}
}
