package munchkin.player;

import munchkin.card.Card;
import munchkin.card.playarea.PlayArea;

public class Hand extends PlayArea<Card> {

	public Hand(){
		super();
	}

	public void putCardInHand(Card card) {
		addACard(card);
	}

}
