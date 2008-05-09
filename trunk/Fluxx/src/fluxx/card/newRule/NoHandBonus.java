package fluxx.card.newRule;

import fluxx.FluxxGame;
import fluxx.rule.draw.NoHandBonusRule;

public class NoHandBonus extends NewRule {

	public NoHandBonus(String name, FluxxGame game) {
		super(name, game);
		_myRule = new NoHandBonusRule(game);
	}

	protected boolean matchesType(NewRule newRule) {
		return newRule instanceof NoHandBonus;
	}

}
