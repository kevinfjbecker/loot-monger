package card;

import player.Player;

public class DiscardFromHandBadStuff implements IDoesBadStuff {

	private int numberOfDiscards;

	public DiscardFromHandBadStuff(int numberOfDiscards) {
		this.numberOfDiscards = numberOfDiscards;
	}

	public void doBadStuff(Player player) {
		player.inform_discardCardsFromHand(numberOfDiscards);
	}

	public String toString() {
		return ("bad stuff: discard " + numberOfDiscards + " from hand");
	}
}
