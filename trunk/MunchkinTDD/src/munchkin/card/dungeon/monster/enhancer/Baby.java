package munchkin.card.dungeon.monster.enhancer;

import munchkin.card.strategy.implementation.GetLevelModifier;
import munchkin.card.strategy.implementation.GetTreasuresModifier;


public class Baby extends MonsterEnhancer {

	public Baby(){
		super();
		getStrategyContext().addStrategy(new GetLevelModifier(-5));
		getStrategyContext().addStrategy(new GetTreasuresModifier(-1));
	}

	public String getCardText() {
		String s = "";
		s += "Baby\n";
		s += "-5 to Level of Monster (minimum level 1)\n";
		s += "Play during combat. If the moster is defeated, draw 1 less Treasure (minimum 1).";
		return (s);
	}
}
