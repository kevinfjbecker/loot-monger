package strategy;

public abstract class AStrategyDecorator extends AStrategy {

	private AStrategy _nextStrategy;

	private AStrategyDecorator _previousStrategyDecorator;

	private CStrategyContext _strategyContext;

	public AStrategyDecorator(IStrategyType type) {
		super(type);
	}

	public void overrideStrategyInContext(CStrategyContext strategyContext) {
		setContext(strategyContext);
		setNextStrategy(strategyContext.replaceEquivalentStrategyWith(this));
		if (getNextStrategy() instanceof AStrategyDecorator)
			((AStrategyDecorator) getNextStrategy())
					.setPreviousStrategyDecorator(this);
	}

	private void setPreviousStrategyDecorator(
			AStrategyDecorator strategyDecorator) {
		_previousStrategyDecorator = strategyDecorator;
	}

	public void removeFromContext() {
		if (getPreviousStrategyDecorator() == null) {
			getStrategyContext().replaceEquivalentStrategyWith(
					getNextStrategy());
			setNextStrategy(null);
		} else {
			this.getPreviousStrategyDecorator().setNextStrategy(
					getNextStrategy());
		}
	}

	private CStrategyContext getStrategyContext() {
		return (_strategyContext);
	}

	private AStrategyDecorator getPreviousStrategyDecorator() {
		return (_previousStrategyDecorator);
	}

	protected AStrategy getNextStrategy() {
		return _nextStrategy;
	}

	private void setContext(CStrategyContext strategyContext) {
		this._strategyContext = strategyContext;
	}

	private void setNextStrategy(AStrategy nextStrategy) {
		this._nextStrategy = nextStrategy;
	}

}
