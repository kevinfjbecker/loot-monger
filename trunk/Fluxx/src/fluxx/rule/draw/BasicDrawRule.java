package fluxx.rule.draw;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.BasicRule;
import fluxx.rule.Rule;


public class BasicDrawRule extends BasicRule implements DrawRule {

	private Integer _x;
	
	public BasicDrawRule(FluxxGame game) {
		super(game);
		_x = new Integer(1);
	}

	@Override
	public void follow(Player player) {
		for (int i = 0; i < _x.intValue(); i++) {
			player.draw();
		}
	}

	@Override
	protected boolean matchesType(Rule rule) {
			return rule instanceof DrawRule;
	}

}
