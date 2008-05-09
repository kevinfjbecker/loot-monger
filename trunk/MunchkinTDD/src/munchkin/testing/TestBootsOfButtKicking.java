package munchkin.testing;

import munchkin.card.terasure.item.BootsOfButtKicking;
import junit.framework.TestCase;

public class TestBootsOfButtKicking extends TestCase {

	BootsOfButtKicking bootsOfButtKicking;
	
	public void setUp(){
		bootsOfButtKicking = new BootsOfButtKicking();
	}
	
	public void testBonusAndValue(){
		assertEquals(2, bootsOfButtKicking.getBonus());
		assertEquals(400,bootsOfButtKicking.getValueInGoldPieces());
	}
	
}
