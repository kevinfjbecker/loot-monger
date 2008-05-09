package munchkin.testing;

import junit.framework.TestCase;
import munchkin.card.TreasureCard;
import munchkin.card.terasure.item.BadAssBandana;
import munchkin.character.race.Dwarf;
import munchkin.deck.Deck;
import munchkin.player.Player;

public class TestBadAssBandana extends TestCase {
	
	BadAssBandana bandana;
	Player player;
	Dwarf dwarf;
	Deck<TreasureCard> deck;
	
	public void setUp(){
		bandana = new BadAssBandana();
		player = new Player();
		dwarf = new Dwarf();
		deck = new Deck<TreasureCard>();
		deck.addACard(bandana);
	}
	
	public void testCarryBandanaThenEquip(){
		assertEquals(0, player.getNumberOfCardsInHand());
		assertEquals(0, player.getNumberCarriedItems());
		player.drawACard(deck);
		assertEquals(1, player.getNumberOfCardsInHand());
		assertEquals(0, player.getNumberCarriedItems());
		player.carryItem(bandana);
		assertEquals(0, player.getNumberOfCardsInHand());
		assertEquals(1, player.getNumberCarriedItems());
		player.equipItem(bandana);
		assertEquals(0, player.getNumberOfCardsInHand());
		assertEquals(0, player.getNumberCarriedItems());
		assertEquals(1, player.getNumberEquipedItems());
		assertEquals(4,player.getEffectiveLevel());
		assertEquals(3,player.getEquipmentBonus());
		player.unequipItem(bandana);
		assertEquals(0, player.getNumberOfCardsInHand());
		assertEquals(1, player.getNumberCarriedItems());
		assertEquals(0, player.getNumberEquipedItems());
		assertEquals(1,player.getEffectiveLevel());
		assertEquals(0,player.getEquipmentBonus());
	}
	
	public void testAbleToEquip(){
		assertTrue(bandana.isUsableBy(player));
		player.setRace(dwarf);
		assertFalse(bandana.isUsableBy(player));
	}
	
	public void testEquipFromHandHand(){
		assertEquals(0, player.getNumberOfCardsInHand());
		assertEquals(0, player.getNumberEquipedItems());
		player.drawACard(deck);
		assertEquals(1, player.getNumberOfCardsInHand());
		assertEquals(0, player.getNumberEquipedItems());
		assertEquals(1,player.getEffectiveLevel());
		assertEquals(0,player.getEquipmentBonus());
		player.equipItem(bandana);
		assertEquals(0, player.getNumberOfCardsInHand());
		assertEquals(1, player.getNumberEquipedItems());
		assertEquals(4,player.getEffectiveLevel());
		assertEquals(3,player.getEquipmentBonus());
	}
	
	public void testGetValue(){
		assertEquals(400,bandana.getValueInGoldPieces());
	}

}
