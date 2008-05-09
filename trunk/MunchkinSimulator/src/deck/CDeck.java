package deck;

import java.util.ArrayList;
import java.util.Collections;

import card.CCard;

public class CDeck<card extends CCard> {
	private ArrayList<card> cardList;
	private ArrayList<card> discardPile;

	public card drawACard() {
		if (cardList.isEmpty()) {
			cardList.addAll(discardPile);
			discardPile.clear();
		}
		return (cardList.remove(0));
	}

	public boolean hasRunOutAndThereAreNoDiscards() {
		if (cardList.isEmpty() && discardPile.isEmpty())
			return (true);
		return (false);
	}

	public ArrayList<card> getDiscardPile(){
		return(discardPile);
	}
	
	public void setDiscardPile(ArrayList<card> discardPile) {
		this.discardPile = discardPile;
	}

	public int size() {
		return (cardList.size());
	}

	public void setCardList(ArrayList<card> cardList) {
		this.cardList = cardList;
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
}
