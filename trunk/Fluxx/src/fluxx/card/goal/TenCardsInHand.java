package fluxx.card.goal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fluxx.FluxxGame;
import fluxx.player.Player;

public class TenCardsInHand extends Goal {

	public TenCardsInHand(String name, FluxxGame game) {
		super(name, game);
	}

	public Player checkForWinner() {
		ArrayList<Player> sortingList;
		sortingList = new ArrayList<Player>(_game.getPlayers());
		Collections.sort(sortingList, new Comparator<Player>() {
			public int compare(Player p0, Player p1) {
				return p1.getCardsInHand().size() - p0.getCardsInHand().size();
			}
		});
		if (sortingList.get(0).getCardsInHand().size() >= 10
				&& (sortingList.size() == 1 || sortingList.get(0)
						.getCardsInHand().size() > sortingList.get(1)
						.getCardsInHand().size()))
			return sortingList.get(0);
		return null;

	}

}
