
package card;

import player.Player;

public class DiscardCarriedItemsBadStuff implements IDoesBadStuff {

	private int numberOfDiscards;

	public DiscardCarriedItemsBadStuff(int numberOfDiscards) {
		this.numberOfDiscards = numberOfDiscards;
	}

	public void doBadStuff(Player player) {
		player.inform_discardCarriedItems(numberOfDiscards);
	}

	public String toString() {
		return ("bad stuff: discard " + numberOfDiscards + " from hand");
	}
	
}
