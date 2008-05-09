package fluxx.card.action;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.player.Player;

public class ExchangeKeepers extends Action {

	public ExchangeKeepers(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		
		if(_player.getKeepersInPlay().isEmpty())
			return;
		
		Player otherPlayer = _player.chooseAPlayer(_game
				.getOtherPlayers(_player));

		if (otherPlayer == null)
			return;

		if (otherPlayer.getKeepersInPlay().isEmpty())
			return;
		
		Card cardToGive = _player.chooseACard(_player.getKeepersInPlay());
		Card cardToTake = _player.chooseACard(otherPlayer.getKeepersInPlay());
		
		otherPlayer.getKeepersInPlay().remove(cardToTake);
		_player.getKeepersInPlay().add(cardToTake);
		cardToTake.setPlayer(_player);
		
		_player.getKeepersInPlay().remove(cardToGive);
		otherPlayer.getKeepersInPlay().add(cardToGive);
		cardToGive.setPlayer(otherPlayer);
	}
}