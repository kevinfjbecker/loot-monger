package munchkin.testing;

import junit.framework.TestCase;
import munchkin.card.dungeon.monster.Amazon;
import munchkin.card.dungeon.monster.enhancer.Ancient;

public class TestAncient extends TestCase {
	
	private Ancient ancient;
	private Amazon amazon;
	
	public void setUp(){
		ancient = new Ancient();
	}
	
	public void testAttachToCard(){
		amazon = new Amazon();
		assertEquals(8,amazon.getLevel());
		assertEquals(2,amazon.getTreasures());
		ancient.attachToMonsterCard(amazon);
		assertEquals(18,amazon.getLevel());
		assertEquals(4, amazon.getTreasures());
	}
	
	public void testdetachFromCard(){
		amazon = new Amazon();
		ancient.attachToMonsterCard(amazon);
		assertEquals(18,amazon.getLevel());
		assertEquals(4, amazon.getTreasures());
		ancient.detachFromCard(amazon);
		assertEquals(8,amazon.getLevel());
		assertEquals(2,amazon.getTreasures());
	}
}
