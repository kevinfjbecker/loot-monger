package palace.logical.phase;

import static palace.event.PalaceEvent.DRAW;
import static palace.logical.card.GameState.END_PLAY_OR_EAT;
import static palace.logical.card.GameState.MAIN_PLAY_OR_EAT;

import java.awt.event.ActionEvent;

import palace.logical.card.LogicalCard;

public class MainDrawBackUpPhase extends AbstractPhase {

	protected void doAction(ActionEvent actionEvent) {

		LogicalCard logicalCard = _gameState.getDeck().draw();
		logicalCard.setFaceUp();
		_gameState.getCurrentPlayer().getHand().add(logicalCard);

	}

	protected void stateTransition() {
		if (!_gameState.getDeck().isEmpty()) {
			if (_gameState.getCurrentPlayer().getHand().size() == 3) {
				_gameState.setState(MAIN_PLAY_OR_EAT);
				_gameState.moveOnToNextPlayer();
			}
		} else {
			_gameState.setState(END_PLAY_OR_EAT);
			_gameState.moveOnToNextPlayer();
		}
	}

	public boolean validateAction(ActionEvent actionEvent) {
		return actionEvent.getID() == DRAW;
	}

}
