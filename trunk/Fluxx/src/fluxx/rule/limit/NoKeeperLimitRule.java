package fluxx.rule.limit;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.BasicRule;
import fluxx.rule.Rule;

public class NoKeeperLimitRule extends BasicRule implements KeeperLimitRule {

	public NoKeeperLimitRule(FluxxGame game) {
		super(game);
	}

	public void follow(Player player) {
	}

	protected boolean matchesType(Rule rule) {
		return rule instanceof KeeperLimitRule;
	}

}
