package munchkin.card.strategy.implementation;

public abstract class AStrategyDecorator extends AStrategy {

	public AStrategyDecorator(IStrategyType methodType) {
		super(methodType);
	}

	public void overrideStrategyInContext(CStrategyContext strategyContext) {
		setContext(strategyContext);
		setNextStrategy(strategyContext.overrideEqivalentStrategyWith(this));
		if (getNextStrategy() instanceof AStrategyDecorator)
			((AStrategyDecorator) getNextStrategy())
					.setPreviousStrategyDecorator(this);
	}

	private void setPreviousStrategyDecorator(
			AStrategyDecorator strategyDecorator) {
		previousStrategyDecorator = strategyDecorator;
	}

	public void removeFromContext() {
		if (getPreviousStrategyDecorator() == null) {
			getStrategyContext().overrideEqivalentStrategyWith(
					getNextStrategy());
			setNextStrategy(null);
		} else {
			this.getPreviousStrategyDecorator().setNextStrategy(
					getNextStrategy());
		}
	}

	private CStrategyContext getStrategyContext() {
		return (strategyContext);
	}

	private AStrategyDecorator getPreviousStrategyDecorator() {
		return (previousStrategyDecorator);
	}

	protected AStrategy getNextStrategy() {
		return nextStrategy;
	}

	private void setContext(CStrategyContext strategyContext) {
		this.strategyContext = strategyContext;
	}

	private void setNextStrategy(AStrategy nextStrategy) {
		this.nextStrategy = nextStrategy;
	}

	private AStrategy nextStrategy;

	private AStrategyDecorator previousStrategyDecorator;

	private CStrategyContext strategyContext;

}
