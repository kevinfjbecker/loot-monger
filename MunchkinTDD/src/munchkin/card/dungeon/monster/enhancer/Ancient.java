package munchkin.card.dungeon.monster.enhancer;

import munchkin.card.strategy.implementation.GetLevelModifier;
import munchkin.card.strategy.implementation.GetTreasuresModifier;


public class Ancient extends MonsterEnhancer {

	public Ancient() {
		super();
		getStrategyContext().addStrategy(new GetLevelModifier(10));
		getStrategyContext().addStrategy(new GetTreasuresModifier(2));
	}

	public String getCardText() {
		String s = "";
		s += "Ancient\n";
		s += "+10 to Level of Monster\n";
		s += "Play during combat. If the moster is defeated, draw 2 extra Treasure cards.";
		return (s);
	}

}
