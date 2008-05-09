package fluxx.card.action;

import java.util.ArrayList;

import fluxx.FluxxGame;
import fluxx.card.Card;

public class DrawThreePlayTwoOfThem extends Action {

	private Integer _numberToDraw;

	private Integer _numberToPlay;

	public DrawThreePlayTwoOfThem(String name, FluxxGame game) {
		super(name, game);
		_numberToDraw = 3;
		_numberToPlay = 2;
	}

	@Override
	protected void doWhateverItSays() {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < _numberToDraw; i++)
			_player.drawOutOfTurn(cards);
		int limitOfCardsToPlay = Math.min(_numberToPlay, cards.size());
		for (int i = 0; i < limitOfCardsToPlay; i++) {
			Card card = _player.chooseACard(cards);
			cards.remove(card);
			_player.playCardOutOfTurn(card, cards);
		}
		for(Card card:cards)
			card.discard();
	}

}
