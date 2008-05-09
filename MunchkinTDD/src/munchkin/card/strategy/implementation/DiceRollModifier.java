
package munchkin.card.strategy.implementation;

import munchkin.card.dungeon.monster.Types;
import munchkin.card.strategy.IDiceRoll;

public class DiceRollModifier extends AStrategyDecorator implements IDiceRoll {

	private int modifier;
	
	public DiceRollModifier(int modifier) {
		super(Types.diceRoll);
		this.modifier = modifier;
	}

	public int roll() {
		return (((IDiceRoll)getNextStrategy()).roll() + modifier );
	}

}
