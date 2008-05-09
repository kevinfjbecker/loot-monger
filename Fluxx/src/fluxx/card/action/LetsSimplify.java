package fluxx.card.action;

import fluxx.FluxxGame;
import fluxx.card.Card;

public class LetsSimplify extends Action {

	public LetsSimplify(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		int maximumToDiscard = (_game.getNewRules().size() + 1) / 2;
		int numberDiscarded = 0;
		while (numberDiscarded < maximumToDiscard && _player.chooseYesOrNo()) {
			Card ruleToDiscard = _player.chooseACard(_game.getNewRules());
			_game.getNewRules().remove(ruleToDiscard);
			ruleToDiscard.discard();
			numberDiscarded++;
		}
	}
	
}
