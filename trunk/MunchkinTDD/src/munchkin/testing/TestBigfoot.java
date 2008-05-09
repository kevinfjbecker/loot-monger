package munchkin.testing;

import junit.framework.TestCase;
import munchkin.card.TreasureCard;
import munchkin.card.dungeon.monster.Bigfoot;
import munchkin.card.terasure.item.BadAssBandana;
import munchkin.character.race.Halfling;
import munchkin.deck.Deck;
import munchkin.player.Player;

public class TestBigfoot extends TestCase {
	
	private Bigfoot bigfoot;
	private Halfling halfling;
	private Player player;
	private Deck<TreasureCard> deck;
	private BadAssBandana bandana;
	
	public void setUp(){
		bigfoot = new Bigfoot();
		halfling = new Halfling();
		player = new Player();
		deck = new Deck<TreasureCard>();
		bandana = new BadAssBandana();
		deck.addACard(bandana);
	}
	
	public void testBadStuff(){
		player.drawACard(deck);
		player.equipItem(bandana);
		bigfoot.doBadStuff(player);
		assertEquals(0,player.getNumberEquipedItems());
		assertEquals(1,deck.getNumberOfDiscards());
	}
	
	public void testGetlevel(){
		assertEquals(12, bigfoot.getEffectiveLevel(player));
		player.setRace(halfling);
		assertEquals(15, bigfoot.getEffectiveLevel(player));
	}
	
	public void testGetTreasure(){
		assertEquals(3,bigfoot.getTreasures());
	}
}
