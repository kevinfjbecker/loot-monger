package palace.logical.phase;

import static palace.event.PalaceEvent.DRAW;
import static palace.logical.card.GameState.SETUP_THREE_OPEN_PLAY;

import java.awt.event.ActionEvent;

import palace.logical.card.LogicalCard;
import palace.logical.card.LogicalPlayer;


public class SetupSixFaceUpPhase extends AbstractPhase {

	protected void doAction(ActionEvent actionEvent) {
		LogicalCard logicalCard = _gameState.getDeck().draw();
		logicalCard.setFaceUp();
		_gameState.getCurrentPlayer().getHand().add(logicalCard);
	}

	private boolean eachPlayerHasCardsInHand() {
		for (LogicalPlayer player : _gameState.getEachOfThePlayers()) {
			if (player.getHand().size() < 6)
				return false;
		}
		return true;
	}

	protected void stateTransition() {
		if (eachPlayerHasCardsInHand()) {
			_gameState.setState(SETUP_THREE_OPEN_PLAY);
		}
		_gameState.moveOnToNextPlayer();
	}

	protected boolean validateAction(ActionEvent actionEvent) {
		return actionEvent.getID() == DRAW;
	}
}
