package fluxx.card.action;

import fluxx.FluxxGame;
import fluxx.card.Card;

public class TrashANewRule extends Action {

	public TrashANewRule(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		
		if(_game.getNewRules().isEmpty())
			return;
		
		Card card = _player.chooseACard(_game.getNewRules());
		_game.getNewRules().remove(card);
		card.discard();
	}

}
