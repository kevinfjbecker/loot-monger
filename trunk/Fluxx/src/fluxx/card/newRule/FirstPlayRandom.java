package fluxx.card.newRule;

import fluxx.FluxxGame;
import fluxx.rule.play.FirstPlayRandomRule;

public class FirstPlayRandom extends NewRule {

	public FirstPlayRandom(String name, FluxxGame game) {
		super(name, game);
		_myRule = new FirstPlayRandomRule(game);
	}

	protected boolean matchesType(NewRule newRule) {
		return newRule instanceof FirstPlayRandom;
	}

}
