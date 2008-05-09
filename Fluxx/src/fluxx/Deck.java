package fluxx;

import java.util.ArrayList;
import java.util.Collections;

import fluxx.card.Card;

public class Deck {

	private ArrayList<Card> _cards;

	private FluxxGame _game;

	public Deck(FluxxGame game) {
		_game = game;
		_cards = new ArrayList<Card>(new CardFactory(game).getCards());
		shuffle();
	}

	public ArrayList<Card> getCards() {
		return _cards;
	}

	public int getNumberOfCardsInDeck() {
		return _cards.size();
	}

	public boolean isEmpty() {
		return _cards.isEmpty();
	}

	public void shuffle() {
		Collections.shuffle(_cards);
	}

	public Card takeTopCard() {
		if (_cards.isEmpty()) {
			_cards.addAll(_game.getDiscardPile());
			_game.getDiscardPile().clear();
			shuffle();
		}
		if (_cards.isEmpty()) {
			
			// test code:
			// System.out.println(">>> Both Deck and Discard Pile are empty.
			// <<<");

			return null;
		}
		return _cards.remove(0);
	}
}
