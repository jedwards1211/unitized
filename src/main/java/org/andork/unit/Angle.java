package org.andork.unit;

import java.math.BigDecimal;

public class Angle extends UnitType<Angle> {
	public static class AngleUnit extends Unit<Angle> {
		public AngleUnit(Angle type, String id, BigDecimal fromBaseFactor, BigDecimal toBaseFactor, double range) {
			super(type, id, fromBaseFactor, toBaseFactor);
			this.range = new UnitizedDouble<>(range, this);
		}

		public final UnitizedDouble<Angle> range;
	}

	public static final Angle type;
	public static final AngleUnit degrees;
	public static final AngleUnit radians;
	public static final AngleUnit gradians;
	public static final AngleUnit percentGrade;

	public static final AngleUnit milsNATO;

	static {
		type = new Angle();
		type.addUnit(radians = new AngleUnit(type, "rad", BigDecimal.ONE, BigDecimal.ONE, Math.PI * 2.0));
		type.addUnit(degrees = new AngleUnit(type, "deg",
				new BigDecimal(180).divide(new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148"), 64, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148").divide(new BigDecimal(180), 64, BigDecimal.ROUND_HALF_EVEN),
				360.0));
		type.addUnit(gradians = new AngleUnit(type, "grad",
				new BigDecimal(200).divide(new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148"), 64, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148").divide(new BigDecimal(200), 64, BigDecimal.ROUND_HALF_EVEN),
				400.0));
		type.addUnit(milsNATO = new AngleUnit(type, "mil",
				new BigDecimal(3200).divide(new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148"), 64, BigDecimal.ROUND_HALF_EVEN),
				new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148").divide(new BigDecimal(3200), 64, BigDecimal.ROUND_HALF_EVEN),
				6400));
		
		type.addUnit(percentGrade = new AngleUnit(type, "% grade", null, null, Double.POSITIVE_INFINITY) {
			@Override
			public BigDecimal fromBaseFactor() {
				throw new UnsupportedOperationException("percent grade is not a linear unit");
			}

			@Override
			public BigDecimal toBaseFactor() {
				throw new UnsupportedOperationException("percent grade is not a linear unit");
			}
			
			public BigDecimal fromBase(BigDecimal angle) {
				return new BigDecimal(Math.tan(angle.doubleValue())).scaleByPowerOfTen(2);
			}
			
			public BigDecimal toBase(BigDecimal angle) {
				return new BigDecimal(Math.atan(angle.scaleByPowerOfTen(-2).doubleValue()));
			}
		});
	}

	public static void main(String[] args) {
		System.out.println(type.convert(360.0, degrees, radians));
		System.out.println(type.convert(Math.PI * 2, radians, degrees));
		System.out.println(type.convert(400.0, gradians, degrees));
		System.out.println(type.convert(400.0, gradians, radians));
	}

	private Angle() {

	}

	@Override
	public double convert(double d, Unit<Angle> from, Unit<Angle> to) {
		return to.fromBase(from.toBase(new BigDecimal(d))).doubleValue();
	}

	public static double sin(UnitizedNumber<Angle> angle) {
		return Math.sin(angle.doubleValue(radians));
	}

	public static double cos(UnitizedNumber<Angle> angle) {
		return Math.cos(angle.doubleValue(radians));
	}

	public static double tan(UnitizedNumber<Angle> angle) {
		return Math.tan(angle.doubleValue(radians));
	}

	public static UnitizedDouble<Angle> asin(double value) {
		return new UnitizedDouble<>(Math.asin(value), Angle.radians);
	}

	public static UnitizedDouble<Angle> acos(double value) {
		return new UnitizedDouble<>(Math.acos(value), Angle.radians);
	}

	public static UnitizedDouble<Angle> atan(double value) {
		return new UnitizedDouble<>(Math.atan(value), Angle.radians);
	}

	public static UnitizedDouble<Angle> atan2(UnitizedNumber<Length> y, UnitizedNumber<Length> x) {
		return new UnitizedDouble<>(
				Math.atan2(y.doubleValue(y.unit), x.doubleValue(y.unit)), Angle.radians);
	}

	public static UnitizedDouble<Angle> atan2(double y, double x) {
		return new UnitizedDouble<>(Math.atan2(y, x), Angle.radians);
	}

	public static UnitizedDouble<Angle> normalize(UnitizedDouble<Angle> a) {
		UnitizedDouble<Angle> range = ((AngleUnit) a.unit).range;
		return a.isNegative() ? a.mod(range).add(range) : a.mod(range);
	}

	public static UnitizedDouble<Angle> opposite(UnitizedDouble<Angle> a) {
		UnitizedDouble<Angle> range = ((AngleUnit) a.unit).range;
		return normalize(a.add(range.mul(0.5)));
	}
}
