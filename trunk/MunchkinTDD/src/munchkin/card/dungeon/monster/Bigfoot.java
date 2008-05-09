package munchkin.card.dungeon.monster;

import munchkin.card.strategy.implementation.BasicGetLevelStrategy;
import munchkin.card.strategy.implementation.BasicGetTreasuresStrategy;
import munchkin.character.race.Dwarf;
import munchkin.character.race.Halfling;
import munchkin.player.Player;

public class Bigfoot extends MonsterCard {

	public Bigfoot() {
		super();
		getStrategyContext().addStrategy(new BasicGetLevelStrategy(12));
		getStrategyContext().addStrategy(new BasicGetTreasuresStrategy(3));
	}
	
	public int getPlayerSpecificBonus(Player player) {
		if(player.isRace(new Dwarf()) || player.isRace(new Halfling())){
			return(3);
		}
		return(0);
	}

	public void doBadStuff(Player player) {
		player.loseEquipedHeadgear();
	}
	
	public String getCardText() {
		String s = "";
		s += "Bigfoot\n";
		s += "Level 12\n";
		s += "+3 against Dwarves and Halflings.\n";
		s += "Bad Stuff: Stomps you flat and eats your hat. Lose the Headgear you were wearing.\n";
		s += "3 Treasures";
		return (s);
	}

}
