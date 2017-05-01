package org.andork.unit;

import java.util.Collections;

public class Volume {
	public static final CompositeUnitType type = (CompositeUnitType) Area.type.mul(Length.type);
	
	public static final CompositeUnit cubicKilometers = type.getUnit(Collections.singletonMap(Length.type, Length.kilometers));
	public static final CompositeUnit cubicMeters = type.getUnit(Collections.singletonMap(Length.type, Length.meters));
	public static final CompositeUnit cubicCentimeters = type.getUnit(Collections.singletonMap(Length.type, Length.centimeters));
	public static final CompositeUnit cubicYards = type.getUnit(Collections.singletonMap(Length.type, Length.yards));
	public static final CompositeUnit cubicFeet = type.getUnit(Collections.singletonMap(Length.type, Length.feet));
	public static final CompositeUnit cubicInches = type.getUnit(Collections.singletonMap(Length.type, Length.inches));
}
