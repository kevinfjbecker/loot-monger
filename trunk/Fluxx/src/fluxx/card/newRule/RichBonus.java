package fluxx.card.newRule;

import fluxx.FluxxGame;
import fluxx.rule.play.RichBonusRule;

public class RichBonus extends NewRule{

	public RichBonus(String name, FluxxGame game) {
		super(name, game);
		_myRule = new RichBonusRule(game);
	}

	@Override
	protected boolean matchesType(NewRule newRule) {
		return newRule instanceof RichBonus;
	}

}
