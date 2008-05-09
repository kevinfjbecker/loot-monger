package palace.logical.phase;

import static palace.event.PalaceEvent.DRAW;
import static palace.logical.card.GameState.SETUP_SIX_FACE_UP;

import java.awt.event.ActionEvent;

import palace.logical.card.LogicalCard;
import palace.logical.card.LogicalPlayer;


public class SetupThreeFaceDownPhase extends AbstractPhase {

	public void doAction(ActionEvent actionEvent) {
		if (_gameState.getCurrentPlayer().getCardStackOne().size() == 0) {
			LogicalCard drawnCard = _gameState.getDeck().draw();
			_gameState.getCurrentPlayer().getCardStackOne().add(drawnCard);
		} else if (_gameState.getCurrentPlayer().getCardStackTwo().size() == 0) {
			LogicalCard drawnCard = _gameState.getDeck().draw();
			_gameState.getCurrentPlayer().getCardStackTwo().add(drawnCard);
		} else if (_gameState.getCurrentPlayer().getCardStackThree().size() == 0) {
			LogicalCard drawnCard = _gameState.getDeck().draw();
			_gameState.getCurrentPlayer().getCardStackThree().add(drawnCard);
		}
	}

	protected boolean eachPlayerHasThreeFaceDownCards() {
		for(LogicalPlayer player : _gameState.getEachOfThePlayers()){
			if (player.getCardStackOne().size() != 1)
				return false;
			if (player.getCardStackTwo().size() != 1)
				return false;
			if (player.getCardStackThree().size() != 1)
				return false;
		}
		return true;
	}

	protected void stateTransition() {
		if (eachPlayerHasThreeFaceDownCards())
			_gameState.setState(SETUP_SIX_FACE_UP);
		_gameState.moveOnToNextPlayer();
	}

	protected boolean validateAction(ActionEvent actionEvent) {
		return (actionEvent.getID() == DRAW);
	}

}
