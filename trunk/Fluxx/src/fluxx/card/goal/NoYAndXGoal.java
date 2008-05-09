package fluxx.card.goal;

import fluxx.FluxxGame;
import fluxx.card.keeper.Keeper;
import fluxx.player.Player;

public class NoYAndXGoal extends Goal {

	private Keeper _x;

	private Keeper _y;

	public NoYAndXGoal(String name, FluxxGame game, Keeper x, Keeper y) {
		super(name, game);
		_x = x;
		_y = y;
	}

	public Player checkForWinner() {
		for (Player player : _game.getPlayers())
			if (player.getKeepersInPlay().contains(_y))
				return null;
		for (Player player : _game.getPlayers())
			if (player.getKeepersInPlay().contains(_x))
				return player;
		return null;
	}
}
