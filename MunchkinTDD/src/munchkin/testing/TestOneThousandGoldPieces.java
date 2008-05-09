package munchkin.testing;

import junit.framework.TestCase;
import munchkin.card.terasure.goupalevel.OneThousandGoldPieces;
import munchkin.player.Player;

public class TestOneThousandGoldPieces extends TestCase {
	public TestOneThousandGoldPieces(String name) {
        super(name);
	}
	public void testThatPlayerGoesupALevel() {
		Player player = new Player();
		OneThousandGoldPieces oneThousandGoldPieces ;
		oneThousandGoldPieces = new OneThousandGoldPieces();
		oneThousandGoldPieces.playOn(player);
		assertEquals(2,player.getLevel());
	}
	public static void main(String[] args){
		junit.textui.TestRunner.run(
				TestOneThousandGoldPieces.class);
	}

}
