package munchkin.card.strategy.implementation;

import munchkin.card.dungeon.monster.Types;
import munchkin.card.strategy.IGetTreasuresStrategy;


public class BasicGetTreasuresStrategy extends AStrategy implements IGetTreasuresStrategy {

	private final int treasures ;
	
	protected IGetTreasuresStrategy nextStrategyGetTreasures;

	private IGetTreasuresStrategy previousStrategyGetTreasures;
	
	public BasicGetTreasuresStrategy(int i) {
		super(Types.getTreasures);
		treasures = i;
	}

	public int getTreasures() {
		return (treasures);
	}

	public IGetTreasuresStrategy getNextStrategyGetTreasures() {
		return (nextStrategyGetTreasures);
	}

	public IGetTreasuresStrategy getPreviousStrategyGetTreasures() {
		return (previousStrategyGetTreasures);
	}

	public void setNextStrategyGetTreasures(IGetTreasuresStrategy nextStrategyGetTreasures) {
		this.nextStrategyGetTreasures = nextStrategyGetTreasures;
	}

	public void setPreviousStrategyGetTreasures(IGetTreasuresStrategy previousStrategyGetTreasures) {
		this.previousStrategyGetTreasures = previousStrategyGetTreasures;
	}

}
