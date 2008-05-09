package fluxx.card.goal;

import fluxx.FluxxGame;
import fluxx.card.keeper.Keeper;
import fluxx.player.Player;

public class XAndYGoal extends Goal {

	private Keeper _x;

	private Keeper _y;

	public XAndYGoal(String name, FluxxGame game, Keeper x, Keeper y) {
		super(name, game);
		_x = x;
		_y = y;
	}

	public Player checkForWinner() {
		for (Player player : _game.getPlayers())
			if(isWinner(player))
				return player;
		return null;
	}

	private boolean isWinner(Player player) {
		return player.getKeepersInPlay().contains(_x)
				&& player.getKeepersInPlay().contains(_y);
	}
}
