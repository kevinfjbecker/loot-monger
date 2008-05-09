package fluxx.card.action;

import java.util.ArrayList;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.player.Player;

public class GoFish extends Action {

	public GoFish(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		ArrayList<Card> allTheCards = _game.getAllTheCards();
		Card wishCard = _player.chooseACard(allTheCards);
		for (Player victim : _game.getOtherPlayers(_player))
			if (victim.getCardsInHand().contains(wishCard)) {
				victim.getCardsInHand().remove(wishCard);

				// test code:
				// System.out.println("Fished the wish (from player)");

				_player.take(wishCard);
				if (_player.chooseYesOrNo())
					_player.playCardOutOfTurn(wishCard, _player
							.getCardsInHand());
				return;
			}

		// test code:
		// System.out.print("Didn't get it from the deck, but... ");

		Card drawnCard = _game.getDeck().takeTopCard();
		if (drawnCard == null)
			return;
		_player.take(drawnCard);
		if (drawnCard == wishCard) {

			// test code:
			// System.out.println("Fished the wish (from deck)");

			if (_player.chooseYesOrNo())
				_player.playCardOutOfTurn(wishCard, _player.getCardsInHand());

			return;
		}

		// test code:
		// System.out.println("didn't get it from the deck either.");
	}

}
