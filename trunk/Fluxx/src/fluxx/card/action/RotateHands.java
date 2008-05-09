package fluxx.card.action;

import java.util.ArrayList;

import fluxx.FluxxGame;
import fluxx.card.Card;

public class RotateHands extends Action {

	public enum Direction {
		Left, Right
	};

	public RotateHands(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		ArrayList<Card> swapList = new ArrayList<Card>();
		if (_player.chooseLeftOrRight() == Direction.Left) {

			// test code:
			// System.out.println("Rotating Left");

			swapList.addAll(_game.getPlayers().get(0).getCardsInHand());
			_game.getPlayers().get(0).getCardsInHand().clear();
			for (int i = 0; i < _game.getPlayers().size() - 1; i++) {
				_game.getPlayers().get(i).getCardsInHand().addAll(
						_game.getPlayers().get(i + 1).getCardsInHand());
				_game.getPlayers().get(i + 1).getCardsInHand().clear();
			}
			_game.getPlayers().get(_game.getPlayers().size() - 1)
					.getCardsInHand().addAll(swapList);
		} else {

			// test code:
			// System.out.println("Rotating Right");

			swapList.addAll(_game.getPlayers().get(
					_game.getPlayers().size() - 1).getCardsInHand());
			_game.getPlayers().get(_game.getPlayers().size() - 1)
					.getCardsInHand().clear();
			for (int i = _game.getPlayers().size() - 1; i > 0; i--) {
				_game.getPlayers().get(i).getCardsInHand().addAll(
						_game.getPlayers().get(i - 1).getCardsInHand());
				_game.getPlayers().get(i - 1).getCardsInHand().clear();
			}
			_game.getPlayers().get(0).getCardsInHand().addAll(swapList);
		}
	}

}
