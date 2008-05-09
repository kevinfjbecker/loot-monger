package fluxx.rule.draw;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.DecoratingRule;
import fluxx.rule.Rule;

public class PoorBonusRule extends DecoratingRule implements DrawRule {

	public PoorBonusRule(FluxxGame game) {
		super(game);
	}

	public void follow(Player player) {
		boolean playerHasFewestKeepers = true;
		for (Player otherPlayer : _game.getPlayers())
			if (otherPlayer != player
					&& otherPlayer.getCardsInHand().size() <= player
							.getCardsInHand().size())
				playerHasFewestKeepers = false;

		if (playerHasFewestKeepers) {

			// test code:
			// System.out.println("Poor Bonus: player draws a card.");

			player.draw();
		}
		
		_next.follow(player);
	}

	protected boolean matchesType(Rule rule) {
		return rule instanceof DrawRule;
	}

}
