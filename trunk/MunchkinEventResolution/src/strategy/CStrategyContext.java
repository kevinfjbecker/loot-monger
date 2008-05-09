package strategy;

import java.util.HashMap;

public class CStrategyContext implements IStrategyContext {

	private HashMap<IStrategyType, AStrategy> _strategyMap;

	public CStrategyContext() {
		_strategyMap = new HashMap<IStrategyType, AStrategy>();
	}

	public boolean containsStrategyType(IStrategyType type) {
		return _strategyMap.containsKey(type);
	}

	public void putStrategy(AStrategy strategy) {
		_strategyMap.put(strategy.getType(), strategy);
	}

	public AStrategy replaceEquivalentStrategyWith(AStrategy strategy) {
		AStrategy overriddenStrategy;
		overriddenStrategy = _strategyMap.get(strategy.getType());
		_strategyMap.put(strategy.getType(), strategy);
		return overriddenStrategy;
	}

	public AStrategy getStrategy(IStrategyType type) {
		return _strategyMap.get(type);
	}

}
