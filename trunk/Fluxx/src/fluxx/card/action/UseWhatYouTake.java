package fluxx.card.action;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.player.Player;

public class UseWhatYouTake extends Action {

	public UseWhatYouTake(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		
		Player otherPlayer = _player.chooseAPlayer(_game
				.getOtherPlayers(_player));

		if (otherPlayer == null)
			return;

		if (otherPlayer.getCardsInHand().isEmpty())
			return;

		Card card = otherPlayer.getCardsInHand().remove(
				(int) (Math.random() * otherPlayer.getCardsInHand().size()));

		_player.take(card);
		_player.playCardOutOfTurn(card, _player.getCardsInHand());
	}

}
