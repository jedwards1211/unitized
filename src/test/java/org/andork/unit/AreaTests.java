package org.andork.unit;

import org.junit.Assert;
import org.junit.Test;

public class AreaTests {
	@Test
	public void testConversion() {
		Assert.assertEquals(
				Area.squareMeters(1000000),
				Area.squareKilometers(1).in(Area.squareMeters));
		Assert.assertEquals(
				Area.squareFeet(9),
				Area.squareYards(1).in(Area.squareFeet));
		Assert.assertEquals(
				Area.squareFeet(10.763910416709722), 
				Area.squareMeters(1).in(Area.squareFeet));
	}

	@Test
	public void testMul() {
		Assert.assertEquals(
				Area.squareMeters(8),
				Area.mul(Length.meters(2), Length.meters(4)));
		Assert.assertEquals(
				Area.squareMeters(8),
				Area.mul(Length.meters(2), Length.meters(4).in(Length.feet)));
		Assert.assertEquals(
				Area.squareMeters(4),
				Area.square(Length.meters(2)));
		Assert.assertEquals(
				Area.squareFeet(4),
				Area.square(Length.feet(2)));
	}
	
	@Test
	public void testDivide() {
		Assert.assertEquals(
				Length.feet(2),
				Area.divide(Area.squareFeet(8), Length.feet(4)));
		Assert.assertEquals(
				Length.feet(2),
				Area.divide(Area.squareFeet(8), Length.feet(4).in(Length.meters)));
	}
	
	@Test
	public void testSqrt() {
		Assert.assertEquals(
				Length.feet(3),
				Area.sqrt(Area.squareFeet(9)));
	}
}
