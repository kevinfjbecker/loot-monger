package munchkin.testing;

import junit.framework.TestCase;
import munchkin.card.dungeon.monster.Amazon;
import munchkin.card.dungeon.monster.enhancer.Ancient;
import munchkin.card.dungeon.monster.enhancer.Baby;

public class TestBaby extends TestCase {

	Baby babyOne;
	Baby babyTwo;
	Amazon amazon;
	Ancient ancient;
	
	protected void setUp() throws Exception {
		super.setUp();
		babyOne = new Baby();
		babyTwo = new Baby();
		amazon = new Amazon();
		ancient = new Ancient();
	}
	
	public void testMultipleAttach(){
		babyOne.attachToMonsterCard(amazon);
		ancient.attachToMonsterCard(amazon);
		assertEquals(13,amazon.getLevel());
		babyOne.detachFromCard(amazon);
		assertEquals(18,amazon.getLevel());
	}
	public void testMinimumLevel(){
		babyOne.attachToMonsterCard(amazon);
		babyTwo.attachToMonsterCard(amazon);
		assertEquals(1,amazon.getLevel());
	}

}
