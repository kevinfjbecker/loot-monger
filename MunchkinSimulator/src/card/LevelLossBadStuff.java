package card;

import player.Player;

public class LevelLossBadStuff implements IDoesBadStuff {

	private int levelLoss;

	public LevelLossBadStuff(int levelLoss) {
		this.levelLoss = levelLoss;
	}

	public void doBadStuff(Player player) {
		player.loseLevels(levelLoss);
	}

	public String toString() {
		return ("bad stuff: lose " + levelLoss + " levels");
	}
}
