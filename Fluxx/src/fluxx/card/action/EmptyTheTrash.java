package fluxx.card.action;

import fluxx.FluxxGame;

public class EmptyTheTrash extends Action {

	public EmptyTheTrash(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		_game.getDeck().getCards().addAll(_game.getDiscardPile());
		_game.getDiscardPile().clear();
		_game.getDeck().shuffle();
	}

}
