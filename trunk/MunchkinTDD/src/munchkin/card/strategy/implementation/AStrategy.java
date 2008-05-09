package munchkin.card.strategy.implementation;

public abstract class AStrategy {

	protected IStrategyType methodType;

	protected AStrategy(IStrategyType methodType) {
		this.methodType = methodType;
	}

	public boolean equals(Object object) {
		if (object instanceof IStrategyType)
			return (((IStrategyType) (object)).equals(methodType));
		if (object instanceof AStrategy)
			return (((AStrategy) (object)).methodType.equals(methodType));
		return false;
	}

}
