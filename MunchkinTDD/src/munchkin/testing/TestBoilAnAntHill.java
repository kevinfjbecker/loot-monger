package munchkin.testing;

import junit.framework.TestCase;
import munchkin.card.terasure.goupalevel.BoilAnAntHill;
import munchkin.player.Player;

public class TestBoilAnAntHill extends TestCase {

	BoilAnAntHill boilAnAntHill;
	Player player;
	
	protected void setUp() throws Exception {
		super.setUp();
		boilAnAntHill = new BoilAnAntHill();
		player = new Player();
	}
	
	public void testThatPlayerGoesupALevel() {
		boilAnAntHill.playOn(player);
		assertEquals(2,player.getLevel());
	}

}
