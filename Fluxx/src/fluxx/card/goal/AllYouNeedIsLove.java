package fluxx.card.goal;

import fluxx.FluxxGame;
import fluxx.card.keeper.Keeper;
import fluxx.player.Player;

public class AllYouNeedIsLove extends Goal {

	private Keeper _love;

	public AllYouNeedIsLove(String name, FluxxGame game, Keeper love) {
		super(name, game);
		_love = love;
	}

	public Player checkForWinner() {
		for (Player player : _game.getPlayers())
			if (isWinner(player))
				return player;
		return null;
	}
	
	private boolean isWinner(Player player) {
		return player.getKeepersInPlay().contains(_love)
				&& player.getKeepersInPlay().size() == 1;
	}

}
