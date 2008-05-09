package munchkin.card.strategy.implementation;

import munchkin.card.dungeon.monster.MonsterCard;
import munchkin.card.dungeon.monster.Types;
import munchkin.card.strategy.IDiceRoll;
import munchkin.card.strategy.IRunAway;
import munchkin.card.terasure.item.IGetStrategyContext;
import munchkin.dice.SixSidedDie;

public class BasicRunAwayStrategy extends AStrategy implements IRunAway,
		IGetStrategyContext {

	private CStrategyContext strategies;

	public CStrategyContext getStrategyContext() {
		return (strategies);
	}

	public BasicRunAwayStrategy() {
		super(Types.runAway);
		strategies = new CStrategyContext();
		getStrategyContext().addStrategy(new SixSidedDie());
	}

	public boolean runAway(MonsterCard monsterCard) {
		return (evaluateGetAwayRoll(rollToGetAway()));
	}

	public int rollToGetAway() {
		return (roll());
	}

	public boolean evaluateGetAwayRoll(int getAwayRoll) {
		if (getAwayRoll >= 5)
			return (true);
		return (false);
	}

	public int roll() {
		return (((IDiceRoll) getStrategyContext().getStrategy(Types.diceRoll))
				.roll());
	}

}
