package fluxx.card.newRule;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.limit.XHandLimitRule;

public class HandLimitX extends Limit {

	private int _x;
	
	public HandLimitX(String name, FluxxGame game, int x) {
		super(name, game);
		_x = x;
		_myRule = new XHandLimitRule(x, game);
	}

	protected boolean matchesType(NewRule newRule) {
		return newRule instanceof HandLimitX;
	}

	public void play() {
		
		super.play();
		
		for(Player player: _game.getPlayers())
			if(player != _game.getCurrentPlayer())
				while(player.getCardsInHand().size() > _x)
					player.discardFromHand();
	}
	
}
