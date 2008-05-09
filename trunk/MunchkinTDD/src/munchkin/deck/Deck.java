package munchkin.deck;

import java.util.ArrayList;
import java.util.Collections;

import munchkin.card.Card;

public class Deck<CardType extends Card> {
	
	private ArrayList<Card<CardType>> cardList;

	private ArrayList<Card<CardType>> discardPile;

	public Deck() {
		cardList = new ArrayList<Card<CardType>>();
		discardPile = new ArrayList<Card<CardType>>();
	}

	public Card<CardType> drawACard() {
		if (cardList.isEmpty()) {
			cardList.addAll(discardPile);
			discardPile.clear();
		}
		return (cardList.remove(0));
	}


	public void addACard(Card<CardType> card) {
		cardList.add(card);
		card.setDiscardPile(getDiscardPile());
	}

	public boolean hasRunOutAndThereAreNoDiscards() {
		if (cardList.isEmpty() && discardPile.isEmpty())
			return (true);
		return (false);
	}

	public void setDiscardPile(ArrayList<Card<CardType>> discardPile) {
		this.discardPile = discardPile;
	}

	public void shuffleCards() {
		Collections.shuffle(cardList);
	}

	public String toString() {
		String s = "Deck[\n";
		s += "\t" + cardList.size() + " cards in deck\n";
		s += "\t" + discardPile.size() + " cards in discard pile\n";
		s += "]";
		return (s);
	}

	public int getNumberOfDiscards() {
		return (discardPile.size());
	}

	public int getNumberOfCards() {
		return (cardList.size());
	}
	
	public ArrayList<Card<CardType>> getDiscardPile(){
		return(discardPile);
	}
}
