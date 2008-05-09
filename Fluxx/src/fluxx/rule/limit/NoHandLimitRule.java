package fluxx.rule.limit;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.BasicRule;
import fluxx.rule.Rule;

public class NoHandLimitRule extends BasicRule implements HandLimitRule {

	public NoHandLimitRule(FluxxGame game) {
		super(game);
	}

	public void follow(Player player) {
	}

	protected boolean matchesType(Rule rule) {
		return rule instanceof HandLimitRule;
	}

}
