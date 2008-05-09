package fluxx.rule;

import fluxx.FluxxGame;

public abstract class OverridingRule extends Rule {

	public OverridingRule(FluxxGame game) {
		super(game);
	}

	@Override
	protected void attach(Rule rule) {
		rule = descendToBasicRule(rule);
		if (rule._previous == null) {
			replaceTopLevelConnection(rule);
		} else if (rule._previous instanceof OverridingRule) {
			swapOutPrevious(rule);
		} else {
			insertInFrontOf(rule);
		}
		linkForwardTo(rule);
	}

}
