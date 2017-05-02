package org.andork.unit;

import org.junit.Assert;
import org.junit.Test;

public class AngleTests {
	@Test
	public void testNormalize() {
		Assert.assertEquals(
				Angle.degrees(300),
				Angle.normalize(Angle.degrees(300.0)));
		Assert.assertEquals(
				Angle.degrees(300.0),
				Angle.normalize(Angle.degrees(-60.0)));
		Assert.assertEquals(
				Angle.radians(Math.PI * 3 / 2),
				Angle.normalize(Angle.radians(-Math.PI / 2)));
	}

	public void testOpposite() {
		Assert.assertEquals(
				Angle.degrees(120.0),
				Angle.opposite(Angle.degrees(300.0)));
		Assert.assertEquals(
				Angle.degrees(300.0),
				Angle.opposite(Angle.degrees(120.0)));
		Assert.assertEquals(
				Angle.radians(Math.PI * 3 / 2),
				Angle.opposite(Angle.radians(Math.PI / 2)));
		Assert.assertEquals(
				Angle.radians(Math.PI / 2),
				Angle.opposite(Angle.radians(Math.PI * 3 / 2)));
	}
}
