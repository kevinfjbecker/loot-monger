package munchkin.dice;

import munchkin.card.dungeon.monster.Types;
import munchkin.card.strategy.IDiceRoll;
import munchkin.card.strategy.implementation.AStrategy;

public class PhoneyDie extends AStrategy implements IDiceRoll {
	
	private int preSetRoll;

	public PhoneyDie(int i) {
		super(Types.diceRoll);
		this.preSetRoll = i;
	}

	public int roll() {
		return (preSetRoll);
	}

}