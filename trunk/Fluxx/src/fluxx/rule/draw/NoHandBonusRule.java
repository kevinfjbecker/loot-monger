package fluxx.rule.draw;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.DecoratingRule;
import fluxx.rule.Rule;

public class NoHandBonusRule extends DecoratingRule implements DrawRule {

	public NoHandBonusRule(FluxxGame game) {
		super(game);
	}

	public void follow(Player player) {
		if (player.getCardsInHand().size() == 0) {

			// test code:
			// System.out
			// .println("No-Hand Bonus: player draw 3 cards out of turn.");

			for (int i = 0; i < 3; i++)
				player.drawOutOfTurn();
		}
		
		_next.follow(player);
	}

	protected boolean matchesType(Rule rule) {
		return rule instanceof DrawRule;
	}

}
