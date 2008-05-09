package fluxx.card.goal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fluxx.FluxxGame;
import fluxx.player.Player;

public class FiveKeepers extends Goal {

	public FiveKeepers(String name, FluxxGame game) {
		super(name, game);
	}

	public Player checkForWinner() {
		ArrayList<Player> sortingList;
		sortingList = new ArrayList<Player>(_game.getPlayers());
		Collections.sort(sortingList, new Comparator<Player>() {
			public int compare(Player p0, Player p1) {
				return p1.getKeepersInPlay().size()
						- p0.getKeepersInPlay().size();
			}
		});
		if (sortingList.get(0).getKeepersInPlay().size() >= 5
				&& (sortingList.size() == 1 || sortingList.get(0)
						.getKeepersInPlay().size() > sortingList.get(1)
						.getKeepersInPlay().size()))
			return sortingList.get(0);
		return null;
	}
}
