package org.andork.unit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Area extends UnitType<Area> {
	public static final Area type;

	public static class AreaUnit extends Unit<Area> {
		public final Unit<Length> baseUnit;

		public AreaUnit(Unit<Length> baseUnit) {
			super(Area.type, "sq " + baseUnit.id,
					baseUnit.fromBaseFactor().pow(2), baseUnit.toBaseFactor().pow(2));
			this.baseUnit = baseUnit;
		}

	}

	public static final Unit<Area> squareKilometers;
	public static final Unit<Area> squareMeters;
	public static final Unit<Area> squareCentimeters;
	public static final Unit<Area> squareMiles;
	public static final Unit<Area> squareYards;
	public static final Unit<Area> squareFeet;
	public static final Unit<Area> squareInches;

	private static final Set<Unit<Area>> imperialUnits = new HashSet<>();
	private static final Set<Unit<Area>> metricUnits = new HashSet<>();

	private static final Map<Unit<Length>, AreaUnit> squares = new HashMap<>();
	
	private static AreaUnit _square(Unit<Length> unit) {
		AreaUnit result = new AreaUnit(unit);
		squares.put(unit, result);
		return result;
	}
	
	public static Unit<Area> square(Unit<Length> unit) {
		return squares.get(unit);
	}

	static {
		type = new Area();
		type.addUnit(squareKilometers = _square(Length.kilometers));
		type.addUnit(squareMeters = _square(Length.meters));
		type.addUnit(squareCentimeters = _square(Length.centimeters));

		type.addUnit(squareMiles = _square(Length.miles));
		type.addUnit(squareYards = _square(Length.yards));
		type.addUnit(squareFeet = _square(Length.feet));
		type.addUnit(squareInches = _square(Length.inches));

		metricUnits.add(squareKilometers);
		metricUnits.add(squareMeters);
		metricUnits.add(squareCentimeters);

		imperialUnits.add(squareMiles);
		imperialUnits.add(squareYards);
		imperialUnits.add(squareFeet);
		imperialUnits.add(squareInches);
	}

	private Area() {

	}

	public static UnitizedDouble<Area> squareKilometers(double value) {
		return new UnitizedDouble<>(value, squareKilometers);
	}
	
	public static UnitizedDouble<Area> squareMeters(double value) {
		return new UnitizedDouble<>(value, squareMeters);
	}

	public static UnitizedDouble<Area> squareCentimeters(double value) {
		return new UnitizedDouble<>(value, squareCentimeters);
	}

	public static UnitizedDouble<Area> squareMiles(double value) {
		return new UnitizedDouble<>(value, squareMiles);
	}

	public static UnitizedDouble<Area> squareYards(double value) {
		return new UnitizedDouble<>(value, squareYards);
	}

	public static UnitizedDouble<Area> squareFeet(double value) {
		return new UnitizedDouble<>(value, squareFeet);
	}

	public static UnitizedDouble<Area> squareInches(double value) {
		return new UnitizedDouble<>(value, squareInches);
	}

	public static UnitizedDouble<Area> mul(UnitizedDouble<Length> a, UnitizedDouble<Length> b) {
		return new UnitizedDouble<>(a.doubleValue(a.unit) * b.doubleValue(a.unit), squares.get(a.unit));
	}

	public static UnitizedDouble<Area> square(UnitizedDouble<Length> a) {
		return mul(a, a);
	}

	public static UnitizedDouble<Length> sqrt(UnitizedDouble<Area> a) {
		return new UnitizedDouble<>(Math.sqrt(a.doubleValue(a.unit)), ((AreaUnit) a.unit).baseUnit);
	}

	public static UnitizedDouble<Length> divide(UnitizedDouble<Area> a, UnitizedDouble<Length> b) {
		Unit<Length> baseUnit = ((AreaUnit) a.unit).baseUnit;
		return new UnitizedDouble<>(a.doubleValue(a.unit) / b.doubleValue(baseUnit), baseUnit);
	}
}
