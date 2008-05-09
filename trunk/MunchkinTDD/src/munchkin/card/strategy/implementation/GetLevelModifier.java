package munchkin.card.strategy.implementation;

import munchkin.card.dungeon.monster.Types;
import munchkin.card.strategy.IGetLevel;

public class GetLevelModifier extends AStrategyDecorator implements IGetLevel {

	private final int levelBonus;

	public GetLevelModifier(int levelBonus) {
		super(Types.getLevel);
		this.levelBonus = levelBonus;
	}

	public int getLevel() {
		return (levelBonus + ((IGetLevel) getNextStrategy()).getLevel());
	}
}
