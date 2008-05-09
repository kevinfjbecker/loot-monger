package munchkin.card.strategy;

public interface IGetTreasuresStrategy extends IGetTreasures {
	
	public IGetTreasuresStrategy getNextStrategyGetTreasures();

	public IGetTreasuresStrategy getPreviousStrategyGetTreasures();

	public void setNextStrategyGetTreasures(IGetTreasuresStrategy getTreasures);

	public void setPreviousStrategyGetTreasures(IGetTreasuresStrategy getTreasures);

}
