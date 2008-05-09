package fluxx.rule.play;

import fluxx.FluxxGame;
import fluxx.player.Player;

public class PlayRuleExecutor {

	private FluxxGame _game;

	public PlayRuleExecutor(FluxxGame game) {
		_game = game;
	}

	public final void follow(Player player) {

		// test code:
		// int numberOfCardsToPlayAtStartOfPhase =
		// _game.getPlayRule().intValue();

		while (_game.getNumberOfCardsPlayed() < _game.getPlayRule()
				.numberToBePlayed()
				&& player.getCardsInHand().size() != 0) {

			player.playCard();

			/*
			 * The game ends immediatly, if the Goal conditions are met.
			 */
			if (_game.getGoal() != null
					&& _game.getGoal().checkForWinner() != null)
				break;
		}

		// test code:
		// System.out.println("\nAt the start of the phase "
		// + numberOfCardsToPlayAtStartOfPhase
		// + " card(s) were to be played; "
		// + _game.getNumberOfCardsPlayed()
		// + " card(s) were played.\nThe player's hand contained "
		// + player.getCardsInHand().size()
		// + " card(s), and the play rule called for "
		// + _game.getPlayRule().intValue() + " card(s) to be played.\n");

	}

}
