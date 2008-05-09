package munchkin.card.terasure.item;

public class BootsOfButtKicking extends ItemCard implements Footgear {

	/*
	 * constructor
	 */
	public BootsOfButtKicking() {
		super();
	}

	public int getBonus() {
		return (2);
	}

	public int getValueInGoldPieces() {
		return (400);
	}

	public String getCardText() {
		String s = "";
		s += "Boots of Butt-Kicking\n";
		s += "+2 Bonus\n";
		s += "Footgear\n";
		s += "400 Gold Pieces";
		return (s);
	}

}
