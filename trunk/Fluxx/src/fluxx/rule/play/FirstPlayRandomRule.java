package fluxx.rule.play;

import fluxx.FluxxGame;
import fluxx.player.Player;
import fluxx.rule.DecoratingRule;
import fluxx.rule.Rule;

public class FirstPlayRandomRule extends DecoratingRule implements PlayRule {

	public FirstPlayRandomRule(FluxxGame game) {
		super(game);
	}

	public void follow(Player player) {

		if (_game.getPlayRule().numberToBePlayed() != 1
				&& !player.getCardsInHand().isEmpty()) {

			// test code:
			// System.out.println(player.getName()
			// + " plays the first card at random.");

			player.playCardAtRandom();
		} else {

			// test code:
			// System.out.println(player.getName()
			// + " does not need to play the first card at random.");
		}

		/*
		 *  i've been removed
		 */
		if (_next != null)
			_next.follow(player);
		else {
			// test code:
			// System.err.println("FirstPlayRandom._next == null"
			// + _game.getNewRules());
		}
	}

	protected boolean matchesType(Rule rule) {
		return rule instanceof PlayRule;
	}

	public int numberToBePlayed() {
		return ((PlayRule) _next).numberToBePlayed();
	}

}
