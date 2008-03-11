package palace.logical.phase;

import static palace.event.PalaceEvent.PLAY;
import static palace.logical.card.GameState.*;

import java.awt.event.ActionEvent;

import palace.event.PlayEvent;
import palace.logical.card.LogicalCard;
import palace.logical.card.LogicalCardStack;
import palace.logical.card.LogicalHand;
import palace.logical.card.LogicalPlayer;
import palace.visual.card.VisualCardStack;

public class SetupThreeOpenPlayPhase extends AbstractPhase {

	private LogicalCard _cardPlayed;

	private LogicalCardStack _logicalCardStack;

	private LogicalPlayer chooseStartingPlayer() {
		if (compare(_gameState.getOtherPlayer(), _gameState.getPlayer()) < 0)
			return _gameState.getOtherPlayer();
		return _gameState.getPlayer();
	}

	private int compare(LogicalPlayer a, LogicalPlayer b) {
		if (openPlayAreaPointValue(a) == openPlayAreaPointValue(b))
			return 0;
		else if (openPlayAreaPointValue(a) < openPlayAreaPointValue(b))
			return -1;
		return 1;
	}

	protected void doAction(ActionEvent actionEvent) {

		_cardPlayed.getParent().remove(_cardPlayed);

		_logicalCardStack.add(_cardPlayed);
		_cardPlayed.getVisualCard().setFaceUp();
	}

	private boolean eachPlayerHasThreeOpenPlayCards() {
		for (LogicalPlayer player : _gameState.getEachOfThePlayers()) {
			if (player.getCardStackOne().size() != 2)
				return false;
			if (player.getCardStackTwo().size() != 2)
				return false;
			if (player.getCardStackThree().size() != 2)
				return false;
		}
		return true;
	}

	private int getValueShown(LogicalCardStack logicalCardStack) {
		if (logicalCardStack.top().getPointsValue() == 2)
			return 14;
		else
			return logicalCardStack.top().getPointsValue();
	}

	private int openPlayAreaPointValue(LogicalPlayer logicalPlayer) {
		return getValueShown(logicalPlayer.getCardStackOne())
				+ getValueShown(logicalPlayer.getCardStackTwo())
				+ getValueShown(logicalPlayer.getCardStackThree());
	}

	protected void stateTransition() {
		if (eachPlayerHasThreeOpenPlayCards()) {
			_gameState.setState(MAIN_PLAY_OR_EAT);
			_gameState.setCurrentPlayer(chooseStartingPlayer());
			return;
		}
		_gameState.moveOnToNextPlayer();
	}

	protected boolean validateAction(ActionEvent actionEvent) {
		if (actionEvent.getID() != PLAY)
			return false;
		if (!(actionEvent instanceof PlayEvent))
			return false;
		PlayEvent playEvent = (PlayEvent) actionEvent;
		_cardPlayed = ((PlayEvent) actionEvent).getCardPlayed();
		if (_cardPlayed.getPlayer() != _gameState.getCurrentPlayer())
			return false;
		if (!(playEvent.getTarget() instanceof VisualCardStack))
			return false;
		_logicalCardStack = ((VisualCardStack) playEvent.getTarget())
				.getLogicalCardStack();
		if (_logicalCardStack.getPlayer() != _gameState.getCurrentPlayer())
			return false;
		if (!(_cardPlayed.getParent() instanceof LogicalHand))
			return false;
		if (_logicalCardStack.size() != 1)
			return false;
		return true;
	}
}
