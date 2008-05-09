package fluxx.rule.play;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.OverridingRule;
import fluxx.rule.Rule;

public class AllPlayRule extends OverridingRule implements PlayRule {

	private PlayRuleExecutor _executor;

	public AllPlayRule(FluxxGame game) {
		super(game);
		_executor = new PlayRuleExecutor(game);
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
		return _game.getNumberOfCardsPlayed()
				+ _game.getCurrentPlayer().getCardsInHand().size();
	}

}
