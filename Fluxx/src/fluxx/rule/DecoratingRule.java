package fluxx.rule;

import fluxx.FluxxGame;

public abstract class DecoratingRule extends Rule {

	public DecoratingRule(FluxxGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void attach(Rule rule) {
		linkForwardTo(rule);
		replaceTopLevelConnection(rule);
	}

}
