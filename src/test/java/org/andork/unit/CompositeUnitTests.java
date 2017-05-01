package org.andork.unit;

import org.junit.Assert;
import org.junit.Test;

public class CompositeUnitTests {
	@Test
	public void testArea() {
		Assert.assertEquals(
				new UnitizedDouble<>(9, Area.squareFeet), 
				new UnitizedDouble<>(1, Area.squareYards).in(Area.squareFeet));
	}

	@Test
	public void testVolume() {
		Assert.assertEquals(
				new UnitizedDouble<>(27, Volume.cubicFeet), 
				new UnitizedDouble<>(1, Volume.cubicYards).in(Volume.cubicFeet));
	}
}

