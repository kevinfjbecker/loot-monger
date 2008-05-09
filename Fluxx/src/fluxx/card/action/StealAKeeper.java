package fluxx.card.action;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.player.Player;

public class StealAKeeper extends Action {

	public StealAKeeper(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		Player victim = _player.chooseAPlayer(_game.getOtherPlayers(_player));
		if(victim == null)
			return;
		Card keeperToTake = _player.chooseACard(victim.getKeepersInPlay());
		if(keeperToTake == null)
			return;
		victim.getKeepersInPlay().remove(keeperToTake);
		_player.getKeepersInPlay().add(keeperToTake);
	}

}
