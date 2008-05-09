package munchkin.card;

import java.util.ArrayList;

public abstract class Card<T extends Card> {

	protected ArrayList<Card> attachedCards;

	ArrayList<Card<T>> discardPile;

	public Card() {
		attachedCards = new ArrayList<Card>();
	}

	public void putOnDiscardPile() {
		discardPile.add(this);
	}

	public void setDiscardPile(ArrayList<Card<T>> discardPile) {
		this.discardPile = discardPile;
	}

	public abstract String getCardText();

	public void attachCard(Card card) {
		attachedCards.add(card);
	}

	public void detachCard(Card card) {
		attachedCards.remove(card);
	}

	public void discard() {
		for (int i = 0; i < attachedCards.size(); i++) {
			attachedCards.get(i).discard();
		}
		attachedCards.clear();
		putOnDiscardPile();
	}
}
