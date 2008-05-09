package palace.logical.phase;

import static palace.event.PalaceEvent.EAT_PILE;
import static palace.logical.card.GameState.END_PLAY_OR_EAT;
import static palace.logical.card.GameState.MAIN_PLAY_OR_EAT;

import java.awt.event.ActionEvent;

public class ChanceFailedPhase extends AbstractPhase {

	protected void doAction(ActionEvent actionEvent) {

		eatPile();

	}

	protected void stateTransition() {
		if (_gameState.getDeck().isEmpty()) {
			_gameState.setState(END_PLAY_OR_EAT);
		} else {
			_gameState.setState(MAIN_PLAY_OR_EAT);
		}
		_gameState.moveOnToNextPlayer();
	}

	protected boolean validateAction(ActionEvent actionEvent) {
		return actionEvent.getID() == EAT_PILE;
	}

}
