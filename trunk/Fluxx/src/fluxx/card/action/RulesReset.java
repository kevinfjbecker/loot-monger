package fluxx.card.action;

import fluxx.FluxxGame;
import fluxx.card.Card;

public class RulesReset extends Action {

	public RulesReset(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		for (Card newRule : _game.getNewRules())
			newRule.discard();
		_game.getNewRules().clear();
	}

}
