package card;

import player.Player;

public class CMonsterCard extends CDungeonCard implements
		IDoesBadStuff {

	private int level;

	private IDoesBadStuff doesBadStuff;

	private int numberOfTreasures;

	public void setLevel(int level) {
		this.level = level;
	}

	public void setDoesBadStuff(IDoesBadStuff doesBadStuff) {
		this.doesBadStuff = doesBadStuff;
	}

	public void setNumberOfTreasures(int numberOfTreasures) {
		this.numberOfTreasures = numberOfTreasures;
	}

	public int getNumberOfTreasures() {
		return (numberOfTreasures);
	}

	public void doBadStuff(Player player) {
		doesBadStuff.doBadStuff(player);
	}

	public int getEffectiveLevel() {
		return (level);
	}

	public String toString() {
		String s = "SimpleMonsterCard{";
		s += "level:" + level + ", ";
		s += doesBadStuff + "}";
		return (s);
	}

}
