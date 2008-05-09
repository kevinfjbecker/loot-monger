package fluxx.rule;

import fluxx.FluxxGame;

public abstract class BasicRule extends Rule {

	public BasicRule(FluxxGame game) {
		super(game);
	}

	@Override
	protected void attach(Rule rule) {
		descendToBasicRule(rule);
		if(rule._previous == null) {
			replaceTopLevelConnection(rule);
		} else {
			insertInFrontOf(rule);
			rule.clear();
		}
	}

}
