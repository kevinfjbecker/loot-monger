package munchkin.card.strategy.implementation;

import munchkin.card.dungeon.monster.Types;
import munchkin.card.strategy.IGetTreasures;

public class GetTreasuresModifier extends AStrategyDecorator implements
		IGetTreasures {

	private final int TreasuresBonus;

	public GetTreasuresModifier(int TreasuresBonus) {
		super(Types.getTreasures);
		this.TreasuresBonus = TreasuresBonus;
	}

	public int getTreasures() {
		return (TreasuresBonus + ((IGetTreasures) getNextStrategy())
				.getTreasures());
	}
}
