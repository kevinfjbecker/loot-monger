package munchkin.card.dungeon.monster;

import munchkin.card.strategy.IDiceRoll;
import munchkin.card.strategy.implementation.BasicGetLevelStrategy;
import munchkin.card.strategy.implementation.BasicGetTreasuresStrategy;
import munchkin.character.race.Dwarf;
import munchkin.dice.SixSidedDie;
import munchkin.player.Player;

public class OneThousandEightHundredEtcOrcs extends MonsterCard implements
		IDiceRoll {

	public OneThousandEightHundredEtcOrcs() {
		super();
		getStrategyContext().addStrategy(new BasicGetLevelStrategy(10));
		getStrategyContext().addStrategy(new BasicGetTreasuresStrategy(3));
		getStrategyContext().addStrategy(new SixSidedDie());
	}

	public int getEffectiveLevel(Player player) {
		return (getLevel() + getPlayerSpecificBonus(player));
	}

	public int getPlayerSpecificBonus(Player player) {
		if (player.isRace(new Dwarf()))
			return (6);
		return (0);
	}

	public void doBadStuff(Player player) {
		int dieRoll = roll();
		if (dieRoll == 1 || dieRoll == 2) {
			player.die();
		} else {
			player.loseLevels(dieRoll);
		}
	}

	public String getCardText() {
		String s = "";
		s += "3,872 Orcs\n";
		s += "Level 10\n";
		s += "+6 against Dwarves, due to ancient grudges.\n";
		s += "Bad Stuff: Roll a die. On a 1 or 2, they stomp you to death. Otherwise, lose as many Levels as the dice shows.\n";
		s += "3 Treasures";
		return (s);
	}

	public int roll() {
		return (((IDiceRoll) getStrategyContext().getStrategy(Types.diceRoll))
				.roll());
	}

}
