package palace.logical.phase;

import static palace.event.PalaceEvent.DRAW;
import static palace.event.PalaceEvent.EAT_PILE;
import static palace.event.PalaceEvent.PLAY;
import static palace.event.PalaceEvent.PLAY_MULTIPLE;
import static palace.logical.card.GameState.CHANCE_FAILED;
import static palace.logical.card.GameState.END_PLAY_OR_EAT;
import static palace.logical.card.GameState.MAIN_DRAW_BACK_UP;

import java.awt.event.ActionEvent;

import palace.event.PlayMultipleEvent;
import palace.logical.card.LogicalCard;

public class MainPlayOrEatPhase extends AbstractPlayOrEatPhase {

	private boolean _chancePlay;

	private boolean _chancePlayWasSuccessful;

	protected void doAction(ActionEvent actionEvent) {

		// if a card is being played, reset _playAgain
		if ((!isDrawAction(actionEvent) || playerHandHasAtLeastThreeCards())) {
			clearPlayAgain();
		}

		if (isDrawAction(actionEvent)) {
			if (_chancePlay) {

				LogicalCard chanceCard = takeTopCardFromDeck();

				if (isSuccessfulPlay(chanceCard))
					_chancePlayWasSuccessful = true;
				else
					_chancePlayWasSuccessful = false;

				putOnTopOfCardPile(chanceCard);

				if (isATwo(chanceCard)) {
					setPlayAgain();
				}

				if (isATen(chanceCard)) {
					clearCardPile();
					setPlayAgain();
				}

			} else {

				drawACard();
			}
		} else if (actionEvent.getID() == EAT_PILE) {
			eatPile();
		} else if (actionEvent.getID() == PLAY) {
			doPlayAction(actionEvent);
		} else if (actionEvent.getID() == PLAY_MULTIPLE) {
			doPlayMultipleAction(actionEvent);
		}
	}

	private boolean shouldChangePlayerAndGoToEndPlayOrEatPhase() {
		return _gameState.getDeck().isEmpty();

	}

	private boolean shouldGoToChanceFailedPhaseNext() {
		return _chancePlay && !_chancePlayWasSuccessful;
	}

	private boolean shouldGoToMainDrawBackUpPhase() {
		return getCurrentPlayerHand().size() < 3
				&& !_gameState.getDeck().isEmpty();
	}

	private boolean shouldPlayAgainAndGoToEndPlayOrEatPhase() {
		return _playAgain && _gameState.getDeck().isEmpty();
	}

	private boolean shouldPlayAgainAndReturnToMainPlayOrEatPhase() {
		return _playAgain && !_gameState.getDeck().isEmpty();
	}

	// note: the order or guard clauses is non-arbitrary;
	// altering the order of the clauses may allow false states to obtain.
	protected void stateTransition() {

		if (shouldGoToChanceFailedPhaseNext()) {
			_chancePlay = false;
			_gameState.setState(CHANCE_FAILED);
			return;
		}
		_chancePlay = false;

		if (shouldPlayAgainAndGoToEndPlayOrEatPhase()) {
			_gameState.setState(END_PLAY_OR_EAT);
			return;
		}

		if (shouldPlayAgainAndReturnToMainPlayOrEatPhase()) {
			return;
		}

		if (shouldGoToMainDrawBackUpPhase()) {
			_gameState.setState(MAIN_DRAW_BACK_UP);
			return;
		}

		if (shouldChangePlayerAndGoToEndPlayOrEatPhase()) {
			_gameState.setState(END_PLAY_OR_EAT);
			_gameState.moveOnToNextPlayer();
			return;
		}

		// still in main play or eat phase; just go on the the next player
		_gameState.moveOnToNextPlayer();

	}

	protected boolean validateAction(ActionEvent actionEvent) {
		if (actionEvent.getID() == DRAW)
			return validateDrawAction();
		if (actionEvent.getID() == EAT_PILE)
			return validateEatPileAction();
		if (actionEvent.getID() == PLAY)
			return validatePlayAction(actionEvent);
		if (actionEvent.getID() == PLAY_MULTIPLE)
			return validatePlayMultipleAction(actionEvent);
		return false;
	}

	private boolean validateDrawAction() {
		if (getCurrentPlayerHand().size() < 3) {
		} else
			_chancePlay = true;
		return true;
	}

	private boolean validateEatPileAction() {
		return !isPileEmpty();
	}

	private boolean validatePlayAction(ActionEvent actionEvent) {

		// continue if the ActionEvent is really a PlayEvent
		if (!isPlayEvent(actionEvent))
			return false;

		// continue if the target is the CardPile
		if (!isCardPileTargeted(getPlayEvent(actionEvent)))
			return false;

		// make sure the this card was played from the current player's hand
		if (!isPlayedFromCurrentPlayerHand(getCardPlayed(actionEvent)))
			return false;

		if (!isSuccessfulPlay(getCardPlayed(actionEvent)))
			return false;

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

		// continue if each of the cards played is from current player's hand
		for (LogicalCard cardPlayed : cardsPlayed)
			if (!isPlayedFromCurrentPlayerHand(cardPlayed))
				return false;

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
