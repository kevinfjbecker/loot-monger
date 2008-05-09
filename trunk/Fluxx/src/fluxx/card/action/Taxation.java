package fluxx.card.action;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.player.Player;

public class Taxation extends Action {

	public Taxation(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		Card cardToGive;
		for (Player player : _game.getOtherPlayers(_player)) {
			cardToGive = player.chooseACard(player.getCardsInHand());
			if (cardToGive != null) {
				player.getCardsInHand().remove(cardToGive);

				// test code:
				// System.out.println(">>> " + player.getName() + " gives up "
				// + cardToGive + " <<<");

				_player.take(cardToGive);
			}
		}
	}

}
