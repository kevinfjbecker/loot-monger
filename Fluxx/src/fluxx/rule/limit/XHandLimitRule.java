package fluxx.rule.limit;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.OverridingRule;
import fluxx.rule.Rule;

public class XHandLimitRule extends OverridingRule implements HandLimitRule {

	private Integer _x;

	public XHandLimitRule(Integer x, FluxxGame game) {
		super(game);
		_x = x;
	}

	public void follow(Player player) {
		while (player.getCardsInHand().size() > _x.intValue())
			player.discardFromHand();
	}

	protected boolean matchesType(Rule rule) {
		return rule instanceof HandLimitRule;
	}

}
