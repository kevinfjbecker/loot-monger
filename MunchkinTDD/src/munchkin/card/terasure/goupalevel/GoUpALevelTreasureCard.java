package munchkin.card.terasure.goupalevel;

import munchkin.card.TreasureCard;
import munchkin.player.Player;

public abstract class GoUpALevelTreasureCard extends TreasureCard {

	/*
	 *  constructor
	 */
	public GoUpALevelTreasureCard() {
		super();
	}
	
	public void playOn(Player player) {
		player.gainLevels(1);
	}
}
