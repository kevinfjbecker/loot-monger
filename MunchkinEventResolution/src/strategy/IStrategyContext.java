package strategy;

public interface IStrategyContext {

	void putStrategy(AStrategy strategy);

	AStrategy getStrategy(IStrategyType type);

	boolean containsStrategyType(IStrategyType type);

	AStrategy replaceEquivalentStrategyWith(AStrategy strategy);

}
