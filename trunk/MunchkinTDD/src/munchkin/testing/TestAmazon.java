package munchkin.testing;

import junit.framework.TestCase;
import munchkin.card.TreasureCard;
import munchkin.card.dungeon.monster.Amazon;
import munchkin.card.terasure.goupalevel.OneThousandGoldPieces;
import munchkin.character.charcterclass.Cleric;
import munchkin.character.charcterclass.NoClass;
import munchkin.character.sex.Female;
import munchkin.character.sex.Male;
import munchkin.deck.Deck;
import munchkin.player.Player;

public class TestAmazon extends TestCase {

	Amazon amazon;
	Player player;
	Deck<TreasureCard> treasureCardDeck;
	
	public TestAmazon(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		amazon = new Amazon();
		player = new Player();
		treasureCardDeck = new Deck<TreasureCard>();
		treasureCardDeck.addACard(new OneThousandGoldPieces());
		amazon.setTreasureDeck(treasureCardDeck);
	}
	
	public void testWillAttackPlayer(){
		player.setSex(new Male());
		assertTrue(amazon.willAttack(player));
		player.setSex(new Female());
	}
	
	public void testAlternativeConflictResolution(){
		amazon.seekAlternativeConflictResolution(player);
		assertEquals(1,player.getNumberOfCardsInHand());
	}
	
	public void testBadStuff(){
		player.gainLevels(3);
		assertEquals(4,player.getLevel());
		assertTrue(player.isCharacterClass(new NoClass()));
		amazon.doBadStuff(player);
		assertEquals(1, player.getLevel());
		player.setCharacterClass(new Cleric());
		assertTrue(player.isCharacterClass(new Cleric()));
		amazon.doBadStuff(player);
		assertTrue(player.isCharacterClass(new NoClass()));
	}
	
}
