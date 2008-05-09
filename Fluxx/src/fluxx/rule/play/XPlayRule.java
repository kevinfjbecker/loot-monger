package fluxx.rule.play;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.OverridingRule;
import fluxx.rule.Rule;

public class XPlayRule extends OverridingRule implements PlayRule {

	private PlayRuleExecutor _executor;
	
	private Integer _x;

	public XPlayRule(Integer x, FluxxGame game) {
		super(game);
		_executor = new PlayRuleExecutor(game);
		_x = x;
	}

	@Override
	public void follow(Player player) {
		_executor.follow(player);
	}

	protected boolean matchesType(Rule rule) {
		return rule instanceof PlayRule;
	}

	public int numberToBePlayed() {
		return _x.intValue();
	}

}
