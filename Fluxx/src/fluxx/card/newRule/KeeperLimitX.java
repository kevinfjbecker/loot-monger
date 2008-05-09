package fluxx.card.newRule;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.limit.XKeeperLimitRule;

public class KeeperLimitX extends Limit {

	private int _x;
	
	public KeeperLimitX(String name, FluxxGame game, int x) {
		super(name, game);
		_x = x;
		_myRule = new XKeeperLimitRule(x, game);
	}

	protected boolean matchesType(NewRule newRule) {
		return newRule instanceof KeeperLimitX;
	}

	public void play() {
		
		super.play();
		
		for(Player player: _game.getPlayers())
			if(player != _game.getCurrentPlayer())
				while(player.getKeepersInPlay().size() > _x)
					player.discardFromKeepersInPlay();
	}

}
