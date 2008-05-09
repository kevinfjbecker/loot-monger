package fluxx.card.newRule;

import fluxx.FluxxGame;
import fluxx.rule.play.XPlayRule;

public class PlayX extends PlayCards {

	int _x;

	public PlayX(String name, FluxxGame game, int x) {
		super(name, game);
		_x = x;
		_myRule = new XPlayRule(x, game);
	}

	public boolean matchesType(NewRule newRule) {
		return newRule instanceof PlayCards;
	}

}
