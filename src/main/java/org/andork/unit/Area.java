package org.andork.unit;

import java.util.Collections;

public class Area {
	public static final CompositeUnitType type = (CompositeUnitType) Length.type.mul(Length.type);
	
	public static final CompositeUnit squareKilometers = type.getUnit(Collections.singletonMap(Length.type, Length.kilometers));
	public static final CompositeUnit squareMeters = type.getUnit(Collections.singletonMap(Length.type, Length.meters));
	public static final CompositeUnit squareCentimeters = type.getUnit(Collections.singletonMap(Length.type, Length.centimeters));
	public static final CompositeUnit squareYards = type.getUnit(Collections.singletonMap(Length.type, Length.yards));
	public static final CompositeUnit squareFeet = type.getUnit(Collections.singletonMap(Length.type, Length.feet));
	public static final CompositeUnit squareInches = type.getUnit(Collections.singletonMap(Length.type, Length.inches));
}
