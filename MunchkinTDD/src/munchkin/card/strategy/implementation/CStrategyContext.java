package munchkin.card.strategy.implementation;

import java.util.ArrayList;

public class CStrategyContext {

	public ArrayList<AStrategy> strategyList;

	public CStrategyContext() {
		strategyList = new ArrayList<AStrategy>();
	}

	public void addStrategy(AStrategy strategy) {
		strategyList.add(strategy);
	}

	private AStrategy removeStrategy(AStrategy strategy) {
		return (strategyList.remove(strategyList.indexOf(strategy)));
	}

	public AStrategy overrideEqivalentStrategyWith(AStrategy strategy) {
		AStrategy overriddenStrategy;
		overriddenStrategy = removeStrategy(strategy);
		addStrategy(strategy);
		return (overriddenStrategy);
	}

	public AStrategy getStrategy(IStrategyType strategyType) {
		return (strategyList.get(strategyList.indexOf(CDummyStrategy
				.getDummyStrategy(strategyType))));
	}

}
