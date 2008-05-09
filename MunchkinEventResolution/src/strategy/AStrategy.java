package strategy;

public abstract class AStrategy {

	private IStrategyType _type;

	protected AStrategy(IStrategyType type) {
		_type = type;
	}

	public IStrategyType getType() {
		return _type;
	}

}
