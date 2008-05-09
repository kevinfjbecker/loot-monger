package munchkin.card.dungeon.monster;

import munchkin.card.strategy.implementation.IStrategyType;

public abstract class Types {

	public static final IStrategyType getLevel;
	public static final IStrategyType getTreasures;
	public static final IStrategyType diceRoll;
	public static final IStrategyType runAway;
	
	/*
	 *  static initialization block
	 */
	static {	
		getLevel = new CGetLevelStrategyType();
		getTreasures = new CGetTreasuresStrategyType();
		diceRoll = new CDiceRollStrategyType();
		runAway = new CDiceRollStrategyType();
	}
}
