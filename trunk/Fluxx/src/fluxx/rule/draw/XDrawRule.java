package fluxx.rule.draw;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.OverridingRule;
import fluxx.rule.Rule;

public class XDrawRule extends OverridingRule implements DrawRule {

	private Integer _x;

	public XDrawRule(Integer x, FluxxGame game) {
		super(game);
		_x = x;
	}

	public void follow(Player player) {
		for (int i = 0; i < _x.intValue(); i++) {
			player.draw();
		}
	}

	protected boolean matchesType(Rule rule) {
		return rule instanceof DrawRule;
	}

}
