package munchkin.testing;

import junit.framework.TestCase;
import munchkin.card.dungeon.monster.OneThousandEightHundredEtcOrcs;
import munchkin.card.dungeon.monster.enhancer.Ancient;
import munchkin.character.race.Dwarf;
import munchkin.dice.PhoneyDie;
import munchkin.player.Player;

public class TestOneThousandEightHundredEtcOrcs extends TestCase{

	OneThousandEightHundredEtcOrcs orcs ;
	Player player;
	
	public TestOneThousandEightHundredEtcOrcs(String name){
		super(name);
	}
	
	public void setUp(){
		orcs = new OneThousandEightHundredEtcOrcs();
		player = new Player();
	}
	
	public void testGetEffeciveLevel(){
		assertEquals(10,orcs.getEffectiveLevel(player));
		player.setRace(new Dwarf());
		assertEquals(16, orcs.getEffectiveLevel(player));
	}
	
	public void testBadStuffPlayerDies(){
		PhoneyDie phoneyDie = new PhoneyDie(2);
		orcs.getStrategyContext().overrideEqivalentStrategyWith(phoneyDie);
		orcs.doBadStuff(player);
		assertTrue(player.isDead());
	}
	
	public void testBadStuffLoseLevels() {
		PhoneyDie phoneyDie = new PhoneyDie(3);
		orcs.getStrategyContext().overrideEqivalentStrategyWith(phoneyDie);
		player.gainLevels(3);
		orcs.doBadStuff(player);
		assertEquals(1, player.getLevel());
	}
	
	public void testDwarfAndAncient(){
		player.setRace(new Dwarf());
		Ancient ancient = new Ancient();
		ancient.attachToMonsterCard(orcs);
		assertEquals(26, orcs.getEffectiveLevel(player));
		assertEquals(5,orcs.getTreasures());
		ancient.detachFromCard(orcs);
		assertEquals(3,orcs.getTreasures());
		
	}
}
