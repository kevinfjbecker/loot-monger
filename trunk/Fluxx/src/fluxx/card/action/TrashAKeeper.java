package fluxx.card.action;

import java.util.ArrayList;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.player.Player;

public class TrashAKeeper extends Action {

	public TrashAKeeper(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.addAll(_game.getPlayers());
		
		for (int i = players.size() - 1; i >= 0; i--)
			if (players.get(i).getKeepersInPlay().isEmpty())
				players.remove(i);

		if (players.isEmpty())
			return;

		Player victim = _player.chooseAPlayer(players);
		Card keeper = _player.chooseACard(victim.getKeepersInPlay());

		victim.discardFromKeepersInPlay(keeper);
	}

}
