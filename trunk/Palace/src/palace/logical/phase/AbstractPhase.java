package palace.logical.phase;

import java.awt.event.ActionEvent;

import palace.logical.card.GameState;
import palace.logical.card.LogicalCardPile;
import palace.logical.card.LogicalHand;

public abstract class AbstractPhase implements Phase {

	protected GameState _gameState;

	public final void actionPerformed(ActionEvent actionEvent) {
		if (validateAction(actionEvent)) {
			doAction(actionEvent);
			stateTransition();
			_gameState.setLastMoveValid(true);
		} else
			_gameState.setLastMoveValid(false);
	}

	protected abstract void doAction(ActionEvent actionEvent);

	protected void eatPile() {
		while (!isPileEmpty()) {
			getCurrentPlayerHand().add(getCardPile().getTop());
			getCardPile().remove(getCardPile().getTop());
		}
	}

	protected LogicalCardPile getCardPile() {
		return _gameState.getPile();
	}

	protected LogicalHand getCurrentPlayerHand() {
		return _gameState.getCurrentPlayer().getHand();
	}

	protected boolean isPileEmpty() {
		return getCardPile().isEmpty();
	}

	public void setGameState(GameState gameState) {
		_gameState = gameState;
	}

	protected abstract void stateTransition();

	protected abstract boolean validateAction(ActionEvent actionEvent);

}
