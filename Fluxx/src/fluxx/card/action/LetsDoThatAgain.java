package fluxx.card.action;

import java.util.ArrayList;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.card.goal.Goal;

public class LetsDoThatAgain extends Action {

	public LetsDoThatAgain(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.addAll(_game.getDiscardPile());
		for (int i = cards.size() - 1; i >= 0; i--)
			if (cards.get(i) instanceof Goal)
				cards.remove(i);
		Card card = _player.chooseACard(cards);
		if (card == null)
			return;
		_game.getDiscardPile().remove(card);
		_player.take(card);
		_player.playCardOutOfTurn(card, _player.getCardsInHand());
	}

}
