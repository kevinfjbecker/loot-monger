package fluxx.rule.play;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.BasicRule;
import fluxx.rule.Rule;

public class BasicPlayRule extends BasicRule implements PlayRule {

	private PlayRuleExecutor _executor;

	private Integer _x;

	public BasicPlayRule(FluxxGame game) {
		super(game);
		_executor = new PlayRuleExecutor(game);
		_x = 1;
	}

	@Override
	public void follow(Player player) {
		_executor.follow(player);
	}

	@Override
	protected boolean matchesType(Rule rule) {
		return rule instanceof PlayRule;
	}

	public int numberToBePlayed() {
		return _x;
	}

}
