package card;

import java.util.ArrayList;


public abstract class CTreasureCard extends CCard {

	private ArrayList<CTreasureCard> discardPile;

	public void discard() {
		discardPile.add(this);
	}

	public void setDiscardPile(ArrayList<CTreasureCard> discardPile) {
		this.discardPile = discardPile;

	}
}