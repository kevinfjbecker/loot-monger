package fluxx.card.newRule;

import fluxx.FluxxGame;
import fluxx.rule.draw.PoorBonusRule;

public class PoorBonus extends NewRule {

	public PoorBonus(String name, FluxxGame game) {
		super(name, game);
		_myRule = new PoorBonusRule(game);
	}

	protected boolean matchesType(NewRule newRule) {
		return newRule instanceof PoorBonus;
	}

}
