package fluxx.rule.limit;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.OverridingRule;
import fluxx.rule.Rule;

public class XKeeperLimitRule extends OverridingRule implements KeeperLimitRule {

	private Integer _x;

	public XKeeperLimitRule(Integer x, FluxxGame game) {
		super(game);
		_x = x;
	}

	public void follow(Player player) {
		while (player.getKeepersInPlay().size() > _x.intValue())
			player.discardFromKeepersInPlay();
	}

	protected boolean matchesType(Rule rule) {
		return rule instanceof KeeperLimitRule;
	}

}
