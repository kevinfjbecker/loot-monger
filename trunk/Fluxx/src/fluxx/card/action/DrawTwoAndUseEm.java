package fluxx.card.action;

import java.util.ArrayList;

import fluxx.FluxxGame;
import fluxx.card.Card;

public class DrawTwoAndUseEm extends Action {

	private Integer _x;

	public DrawTwoAndUseEm(String name, FluxxGame game) {
		super(name, game);
		_x = new Integer(2);
	}

	@Override
	protected void doWhateverItSays() {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < _x; i++)
			_player.drawOutOfTurn(cards);
		int numberOfCardsActuallyDrawn = cards.size();
		for (int i = 0; i < numberOfCardsActuallyDrawn; i++) {
			Card card = _player.chooseACard(cards);
			cards.remove(card);
			_player.playCardOutOfTurn(card, cards);
		}
	}
}
