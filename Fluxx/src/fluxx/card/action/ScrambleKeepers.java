package fluxx.card.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.player.Player;

public class ScrambleKeepers extends Action {

	public ScrambleKeepers(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		ArrayList<Card> keepers = new ArrayList<Card>();
		HashMap<Player, Integer> keeperCount = new HashMap<Player, Integer>();
		for (Player player : _game.getPlayers()) {
			keeperCount.put(player, player.getKeepersInPlay().size());
			keepers.addAll(player.getKeepersInPlay());
			player.getKeepersInPlay().clear();
		}
		Collections.shuffle(keepers);
		for (Entry<Player, Integer> entry : keeperCount.entrySet())
			for (int i = 0; i < entry.getValue(); i++) {
				Card keeper = keepers.remove(0);
				keeper.setPlayer(entry.getKey());
				entry.getKey().getKeepersInPlay().add(keeper);
			}
	}

}
