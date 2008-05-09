package munchkin.card.strategy.implementation;

public class CDummyStrategy extends AStrategy {
	private CDummyStrategy(IStrategyType methodType) {
		super(methodType);
	}

	public static AStrategy getDummyStrategy(IStrategyType strategyType) {
		return (new CDummyStrategy(strategyType));
	}
}
