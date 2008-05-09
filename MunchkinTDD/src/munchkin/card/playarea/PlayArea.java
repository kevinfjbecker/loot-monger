package munchkin.card.playarea;

import java.util.ArrayList;

import munchkin.card.Card;

public class PlayArea<CardType extends Card> {
	
	public PlayArea() {
		cardList = new ArrayList<CardType>();
	}

	protected ArrayList<CardType> cardList;
	

	public int getNumberOfCards() {
		return (cardList.size());
	}

	protected void addACard(CardType card) {
		cardList.add(card);
	}

	public boolean contains(Card card) {
		return(cardList.contains(card));
	}

	public CardType remove(CardType card) {
		return(cardList.remove(cardList.indexOf(card)));
	}

}
