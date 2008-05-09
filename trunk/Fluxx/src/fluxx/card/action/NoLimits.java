package fluxx.card.action;

import fluxx.FluxxGame;
import fluxx.card.newRule.Limit;

public class NoLimits extends Action {

	public NoLimits(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		for (int i = _game.getNewRules().size() - 1; i >= 0; i--)
			if (_game.getNewRules().get(i) instanceof Limit) {
				_game.getNewRules().remove(i).discard();
			}
	}

}
