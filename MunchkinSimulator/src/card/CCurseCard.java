package card;

import message.event.CurseAppliedEvent;
import player.Player;

public class CCurseCard extends CDungeonCard {

	private int levelLoss;
	
	public void setLevelLoss(int levelLoss) {
		this.levelLoss = levelLoss;
	}

	public void apply(Player player) {
		gameEventNotifier.notifyListeners(new CurseAppliedEvent(player, this));
		player.loseLevels(levelLoss);
	}

	public String toString() {
		String s = "SimpleCurseCard{";
		s += "lose " + levelLoss + " levels}";
		return (s);
	}
}
