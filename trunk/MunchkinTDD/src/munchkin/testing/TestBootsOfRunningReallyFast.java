package munchkin.testing;

import junit.framework.TestCase;
import munchkin.card.TreasureCard;
import munchkin.card.dungeon.monster.Amazon;
import munchkin.card.dungeon.monster.MonsterCard;
import munchkin.card.dungeon.monster.Types;
import munchkin.card.terasure.item.BootsOfRunningReallyFast;
import munchkin.card.terasure.item.IGetStrategyContext;
import munchkin.deck.Deck;
import munchkin.dice.PhoneyDie;
import munchkin.player.Player;

public class TestBootsOfRunningReallyFast extends TestCase {

	private Player player;

	private MonsterCard monster;

	private BootsOfRunningReallyFast boots;

	private Deck<TreasureCard> deck;

	public void setUp() {
		player = new Player();
		monster = new Amazon();
		deck = new Deck<TreasureCard>();
		boots = new BootsOfRunningReallyFast();
		deck.addACard(boots);
	}

	public void testRunAway() {
		((IGetStrategyContext) player.getCharacter().getStrategyContext()
				.getStrategy(Types.runAway)).getStrategyContext()
				.overrideEqivalentStrategyWith(new PhoneyDie(5));
		assertTrue(player.runAway(monster));
		((IGetStrategyContext) player.getCharacter().getStrategyContext()
				.getStrategy(Types.runAway)).getStrategyContext()
				.overrideEqivalentStrategyWith(new PhoneyDie(1));
		assertFalse(player.runAway(monster));
	}

	public void testRunAwayWithBoots() {
		((IGetStrategyContext) player.getCharacter().getStrategyContext()
				.getStrategy(Types.runAway)).getStrategyContext()
				.overrideEqivalentStrategyWith(new PhoneyDie(3));
		assertFalse(player.runAway(monster));
		player.drawACard(deck);
		player.equipItem(boots);
		assertTrue(player.runAway(monster));
		player.loseItem(boots);
		assertFalse(player.runAway(monster));
		assertEquals(0, player.getNumberEquipedItems());
		assertEquals(0, player.getNumberCarriedItems());
		assertEquals(0, deck.getNumberOfCards());
		assertEquals(1, deck.getNumberOfDiscards());
	}
}
