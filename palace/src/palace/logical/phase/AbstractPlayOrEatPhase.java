package palace.logical.phase;

import static palace.event.PalaceEvent.DRAW;
import static palace.logical.card.LogicalCard.Rank.Ten;
import static palace.logical.card.LogicalCard.Rank.Two;

import java.awt.event.ActionEvent;

import palace.event.PlayEvent;
import palace.event.PlayMultipleEvent;
import palace.logical.card.LogicalCard;
import palace.logical.card.LogicalCardPile;
import palace.visual.card.VisualCardPile;

public abstract class AbstractPlayOrEatPhase extends AbstractPhase {

	protected boolean _playAgain;

	protected boolean areAllOfTheSameRank(LogicalCard[] cards) {
		for (int i = 1; i < cards.length; i++)
			if (cards[i].getRank() != cards[i - 1].getRank())
				return false;
		return true;
	}
	
	protected void clearCardPile() {
		while (!isPileEmpty())
			getCardPile().remove(getCardPile().getTop());
	}

	protected void clearPlayAgain() {
		_playAgain = false;
	}

	protected void doPlayAction(ActionEvent actionEvent) {

		playToPile(getCardPlayed(actionEvent));

		if (isATwo(getCardPlayed(actionEvent))) {
			setPlayAgain();
		}

		if (isATen(getCardPlayed(actionEvent))) {
			clearCardPile();
			setPlayAgain();
		}
	}

	protected void doPlayMultipleAction(ActionEvent actionEvent) {

		// cast form ActionEvent to PlayMultipleEvent
		PlayMultipleEvent playMultiple = (PlayMultipleEvent) actionEvent;

		// transfer the cards played to the pile
		for (LogicalCard card : playMultiple.getCardsPlayed()) {
			playToPile(card);
		}

		// play again on Twos
		if (isATwo(playMultiple.getCardsPlayed()[0])) {
			setPlayAgain();
		}

		// clear the pile and play again on Tens
		if (isATen(playMultiple.getCardsPlayed()[0])) {
			clearCardPile();
			setPlayAgain();
		}

		// 4-of-a-kind (and more-of-a-kind) clears too--and play again
		if (playMultiple.getCardsPlayed().length >= 4) {
			clearCardPile();
			setPlayAgain();
		}

	}

	protected void drawACard() {
		LogicalCard logicalCard = takeTopCardFromDeck();
		logicalCard.setFaceUp();
		_gameState.getCurrentPlayer().getHand().add(logicalCard);
	}

	protected LogicalCard getCardPlayed(ActionEvent actionEvent) {
		return getPlayEvent(actionEvent).getCardPlayed();
	}

	protected LogicalCardPile getLogicalCardPile(PlayEvent playEvent) {
		return ((VisualCardPile) playEvent.getTarget()).getLogicalCardPile();
	}

	protected LogicalCardPile getLogicalCardPile(PlayMultipleEvent event) {
		return ((VisualCardPile) event.getTarget()).getLogicalCardPile();
	}
	
	protected PlayEvent getPlayEvent(ActionEvent actionEvent) {
		return (PlayEvent) actionEvent;
	}

	protected int getPointsValueFromTopOfCardPile() {
		return getCardPile().getTop().getPointsValue();
	}

	protected boolean isATen(LogicalCard card) {
		return card.getRank() == Ten;
	}

	protected boolean isATwo(LogicalCard card) {
		return card.getRank() == Two;
	}

	protected boolean isCardPileTargeted(PlayEvent playEvent) {
		if (!(playEvent.getTarget() instanceof VisualCardPile))
			return false;
		if (getLogicalCardPile(playEvent) != getCardPile())
			return false;
		return true;
	}
	
	protected boolean isCardPileTargeted(PlayMultipleEvent playMultipleEvent) {
		if (!(playMultipleEvent.getTarget() instanceof VisualCardPile))
			return false;
		if (getLogicalCardPile(playMultipleEvent) != getCardPile())
			return false;
		return true;
	}

	protected boolean isDrawAction(ActionEvent actionEvent) {
		return actionEvent.getID() == DRAW;
	}

	protected boolean isGreaterOrEqualToTopOfPile(LogicalCard card) {
		return card.getPointsValue() >= getPointsValueFromTopOfCardPile();
	}

	protected boolean isPlayEvent(ActionEvent actionEvent) {
		return actionEvent instanceof PlayEvent;
	}

	protected boolean isPlayedFromCurrentPlayerHand(LogicalCard card) {
		return card.getParent() == getCurrentPlayerHand();
	}

	protected boolean isPlayMultipleEvent(ActionEvent actionEvent) {
		return actionEvent instanceof PlayMultipleEvent;
	}
	
	protected boolean isSuccessfulPlay(LogicalCard card) {
		return isPileEmpty() || (isGreaterOrEqualToTopOfPile(card))
				|| isATwo(card) || isATen(card);
	}

	protected boolean playerHandHasAtLeastThreeCards() {
		return getCurrentPlayerHand().size() >= 3;
	}

	protected void playToPile(LogicalCard card) {
		card.getParent().remove(card);
		putOnTopOfCardPile(card);
	}

	protected void putOnTopOfCardPile(LogicalCard card) {
		getCardPile().add(card);
		card.getVisualCard().setFaceUp();
	}

	protected void setPlayAgain() {
		_playAgain = true;
	}

	protected LogicalCard takeTopCardFromDeck() {
		return _gameState.getDeck().draw();
	}

}
