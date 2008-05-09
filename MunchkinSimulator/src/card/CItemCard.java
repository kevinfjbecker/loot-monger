package card;


public class CItemCard extends CTreasureCard {

	protected int bonus;

	protected int value;

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String toString() {
		String s = "SimpleItemCard{";
		s += "bonus:" + this.bonus + ", ";
		s += "value:" + this.value + "}";
		return (s);
	}

	public int getBonus() {
		return (bonus);
	}

}
