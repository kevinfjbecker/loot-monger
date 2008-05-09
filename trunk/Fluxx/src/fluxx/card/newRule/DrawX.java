package fluxx.card.newRule;

import fluxx.FluxxGame;
import fluxx.rule.draw.XDrawRule;

public class DrawX extends NewRule {

	private int _x;

	public DrawX(String name, FluxxGame game, int x) {
		super(name, game);
		_x = x;
		_myRule = new XDrawRule(x, game);
	}

	public boolean matchesType(NewRule newRule) {
		return newRule instanceof DrawX;
	}

	public void play() {

		super.play();

		// test code:
		// int i = 0;
		// System.out.println("Draw Rule changed to \"Draw " + _x + "\" with "
		// + _game.getNumberOfCardsDrawn() + " cards drawn.");

		while (_game.getNumberOfCardsDrawn() < _x
				&& (!_game.getDeck().isEmpty() && !_game.getDiscardPile()
						.isEmpty())) {

			// test code: i++;

			_player.draw();
		}

		// test code:
		// System.out.println("The player draws " + i + " additional cards.");

	}
}
