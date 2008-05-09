package card;

import java.util.ArrayList;


public abstract class CDungeonCard extends CCard {

	private ArrayList<CDungeonCard> discardPile;
	
	public void discard() {
		discardPile.add(this);
	}

	public void setDiscardPile(ArrayList<CDungeonCard> discardPile) {
		this.discardPile = discardPile;
	}
}
