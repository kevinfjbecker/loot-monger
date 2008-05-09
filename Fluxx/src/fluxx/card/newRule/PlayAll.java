package fluxx.card.newRule;

import fluxx.FluxxGame;
import fluxx.rule.play.AllPlayRule;

public class PlayAll extends PlayCards {

	public PlayAll(String name, FluxxGame game) {
		super(name, game);
		_myRule = new AllPlayRule(game);
	}

	protected boolean matchesType(NewRule newRule) {
		return newRule instanceof PlayCards;
	}

}
