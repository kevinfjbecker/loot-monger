package palace.logical.phase;

import static palace.event.PalaceEvent.EAT_PILE;
import static palace.event.PalaceEvent.PLAY;
import static palace.event.PalaceEvent.PLAY_MULTIPLE;
import static palace.logical.card.GameState.CHANCE_FAILED;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

import palace.event.PlayMultipleEvent;
import palace.logical.card.LogicalCard;
import palace.logical.card.LogicalCardStack;

public class EndPlayOrEatPhase extends AbstractPlayOrEatPhase {

	private boolean _chancePlay;

	private boolean _chancePlayWasSuccessful;

	protected void doAction(ActionEvent actionEvent) {
		clearPlayAgain();
		if (actionEvent.getID() == EAT_PILE) {
			eatPile();
		} else if (actionEvent.getID() == PLAY) {
			doPlayAction(actionEvent);
		} else if (actionEvent.getID() == PLAY_MULTIPLE) {
			doPlayMultipleAction(actionEvent);
		}
	}

	private boolean eachOfThePlayerStacksHasAFacedownCardOrNoCards() {
		return _gameState.getCurrentPlayer().getCardStackOne().size() <= 1
				&& _gameState.getCurrentPlayer().getCardStackTwo().size() <= 1
				&& _gameState.getCurrentPlayer().getCardStackThree().size() <= 1;
	}

	private boolean isCardFromCurrentPlayer(LogicalCard logicalCard) {
		return logicalCard.getPlayer() == _gameState.getCurrentPlayer();
	}

	private boolean isPlayedFromLogicalCardStack(LogicalCard logicalCard) {
		return logicalCard.getParent() instanceof LogicalCardStack;
	}

	protected void stateTransition() {
		if (!_playAgain) {
			if (_chancePlay) {
				_chancePlay = false;
				if (!_chancePlayWasSuccessful)
					_gameState.setState(CHANCE_FAILED);
				else
					_gameState.moveOnToNextPlayer();
			} else {
				_gameState.moveOnToNextPlayer();
			}
		}
	}

	protected boolean validateAction(ActionEvent actionEvent) {
		if (actionEvent.getID() == EAT_PILE)
			return validateEatPileAction();
		if (actionEvent.getID() == PLAY)
			return validatePlayAction(actionEvent);
		if (actionEvent.getID() == PLAY_MULTIPLE)
			return validatePlayMultipleAction(actionEvent);
		return false;
	}

	private boolean validateEatPileAction() {
		return !_gameState.getPile().isEmpty();
	}

	private boolean validatePlayAction(ActionEvent actionEvent) {

		// continue if the ActionEvent is really a PlayEvent
		if (!isPlayEvent(actionEvent))
			return false;

		// continue if the target is a VisualCardPile
		if (!isCardPileTargeted(getPlayEvent(actionEvent)))
			return false;

		// ensure that the current player played this card
		if (!isCardFromCurrentPlayer(getCardPlayed(actionEvent)))
			return false;

		// make sure the this card was played from:
		// 1) the current player's hand, or
		// 2) a CardStack and the player's hand is empty
		if (isPlayedFromCurrentPlayerHand(getCardPlayed(actionEvent))) {
		} else if (isPlayedFromLogicalCardStack(getCardPlayed(actionEvent))) {
			if (_gameState.getCurrentPlayer().getHand().size() != 0) {
				return false;
			}
		}

		// continue if the card was played from a LogicalCardStack
		// and either:
		// 1) the it is a face up (that is, 2 cards are in it's stack), or
		// 2) each of the player's stacks has a facedown card or no card
		if (isPlayedFromLogicalCardStack(getCardPlayed(actionEvent))) {
			if (getCardPlayed(actionEvent).getParent().size() != 2
					&& !eachOfThePlayerStacksHasAFacedownCardOrNoCards())
				return false;
		}

		// mark as chance play if the card is the last card from it's stack
		if (isPlayedFromLogicalCardStack(getCardPlayed(actionEvent))) {
			if (getCardPlayed(actionEvent).getParent().size() == 1) {
				_chancePlay = true;
			}
		}

		// check to see play is successful; that is:
		// 1) played onto an empty pile, or
		// 2) is greater or equal to the card on top of the pile, or
		// 3) is a Two, or
		// 4) is a Ten
		// chance plays are allowed through, but noted as unsuccessful
		if (!isSuccessfulPlay(getCardPlayed(actionEvent)))
			if (_chancePlay)
				_chancePlayWasSuccessful = false;
			else
				return false;
		else if (_chancePlay)
			_chancePlayWasSuccessful = true;
		return true;
	}

	private boolean validatePlayMultipleAction(ActionEvent actionEvent) {

		// make sure that the ActionEvent is a PlayMultipleEvent
		if (!isPlayMultipleEvent(actionEvent))
			return false;

		// cast the ActionEvent to a PlayMultipleEvent
		PlayMultipleEvent playMultipleEvent = (PlayMultipleEvent) actionEvent;

		// continue if the target of the action is the card pile
		if (!isCardPileTargeted(playMultipleEvent))
			return false;

		// get all the cards played
		LogicalCard[] cardsPlayed = playMultipleEvent.getCardsPlayed();

		// make sure all of the cards are from the current player
		for (LogicalCard cardPlayed : cardsPlayed)
			if (!isCardFromCurrentPlayer(cardPlayed))
				return false;

		// note whether each of the cards played is from current player's hand
		boolean allCardsPlayedFromHand = true;
		for (LogicalCard cardPlayed : cardsPlayed)
			if (!isPlayedFromCurrentPlayerHand(cardPlayed)) {
				allCardsPlayedFromHand = false;
				break;
			}

		// in the case that not all of the cards are from the player's hand
		if (!allCardsPlayedFromHand) {

			// continue if the player's hand is empty
			if (_gameState.getCurrentPlayer().getHand().size() == 0) {
			} else {

				// make sure that all of the cards from the hand are played
				List<LogicalCard> played = Arrays.asList(cardsPlayed);
				for (LogicalCard card : _gameState.getCurrentPlayer().getHand())
					if (!played.contains(card))
						return false;
			}

			// continue if all cards played from stacks are face up
			for (LogicalCard card : cardsPlayed)
				if (isPlayedFromLogicalCardStack(card))
					if (card.getParent().size() != 2)
						return false;
		}

		// make sure that all of the cards are of the same Rank
		if (!areAllOfTheSameRank(cardsPlayed))
			return false;

		// ensure that the cards played are:
		// 1) played onto an empty pile, or
		// 2) are greater or equal to the card on top of the pile, or
		// 3) are Twos, or
		// 4) are Tens, or
		// 5) are 4-of-a-kind or more-of-a-kind
		if (!isSuccessfulPlay(cardsPlayed[0]) && cardsPlayed.length < 4)
			return false;

		return true;

	}

}
