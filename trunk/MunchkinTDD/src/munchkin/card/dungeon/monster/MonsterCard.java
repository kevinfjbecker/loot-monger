package munchkin.card.dungeon.monster;

import munchkin.card.DungeonCard;
import munchkin.card.strategy.IGetLevel;
import munchkin.card.strategy.IGetTreasures;
import munchkin.card.strategy.implementation.CStrategyContext;
import munchkin.player.Player;

public abstract class MonsterCard extends DungeonCard implements IGetLevel,
		IGetTreasures {

	private CStrategyContext strategies;

	public CStrategyContext getStrategyContext(){
		return(strategies);
	}
	
	public MonsterCard() {
		super();
		strategies = new CStrategyContext();
	}

	public int getEffectiveLevel(Player player) {
		return (getLevel() + getPlayerSpecificBonus(player));
	}

	/*
	 *  TODO: extract minimum level rule
	 */
	public int getLevel() {
		IGetLevel gl = (IGetLevel) getStrategyContext().getStrategy(Types.getLevel);
		if (gl.getLevel() < 1)
			return (1);
		return (gl.getLevel());
	}

	public abstract int getPlayerSpecificBonus(Player player);

	public abstract void doBadStuff(Player player);

	public int getTreasures() {
		IGetTreasures gt = (IGetTreasures) strategies
				.getStrategy(Types.getTreasures);
		return (gt.getTreasures());
	}
}
