package munchkin.card.terasure.item;

import munchkin.character.race.Human;
import munchkin.player.Player;

public class BadAssBandana extends ItemCard  implements Headgear, RestrictedUse {

	/*
	 *  constructor
	 */
	public BadAssBandana(){
		super();
	}

	public String getCardText() {
		String s = "";
		s += "Bad-Ass Bandanna\n";
		s += "+3 Bonus\n";
		s += "Usable by Human Only\n";
		s += "Headgear\n";
		s += "400 Gold Pieces";
		return (s);
	}

	public boolean isUsableBy(Player player) {
		return (player.isRace(new Human()));
	}

	public int getBonus() {
		return (3);
	}

	public int getValueInGoldPieces() {
		return (400);
	}
}
