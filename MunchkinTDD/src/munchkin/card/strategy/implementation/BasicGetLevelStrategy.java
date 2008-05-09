package munchkin.card.strategy.implementation;

import munchkin.card.dungeon.monster.Types;
import munchkin.card.strategy.IGetLevel;


public class BasicGetLevelStrategy extends AStrategy implements IGetLevel {
	
	private final int level;
	
	public BasicGetLevelStrategy(int i) {
		super(Types.getLevel);
		level = i;
	}

	public int getLevel() {
		return (level);
	}
}
