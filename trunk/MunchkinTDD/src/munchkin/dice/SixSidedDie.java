package munchkin.dice;

import munchkin.card.dungeon.monster.Types;
import munchkin.card.strategy.IDiceRoll;
import munchkin.card.strategy.implementation.AStrategy;

public class SixSidedDie  extends AStrategy implements IDiceRoll {

	public SixSidedDie() {
		super(Types.diceRoll);
	}

	public int roll() {
		return (((int) (Math.random() * 6.0)) + 1);
	}

}