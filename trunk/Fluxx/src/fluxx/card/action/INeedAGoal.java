package fluxx.card.action;

import java.util.ArrayList;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.card.goal.Goal;
import fluxx.player.Player;

public class INeedAGoal extends Action {

	public INeedAGoal(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		ArrayList<Card> cardsFromWhichToChoose = new ArrayList<Card>();
		for (Player victim : _game.getOtherPlayers(_player)) {
			ArrayList<Card> goalsInHand = new ArrayList<Card>();
			for (Card card : victim.getCardsInHand())
				if (card instanceof Goal)
					goalsInHand.add(card);
			if(!goalsInHand.isEmpty()){
				Card cardToGive =victim.chooseACard(goalsInHand); 
				cardsFromWhichToChoose.add(cardToGive);
				victim.getCardsInHand().remove(cardToGive);
			} else {
				Card cardFromDeck = _game.getDeck().takeTopCard();
				if(cardFromDeck != null)
					cardsFromWhichToChoose.add(cardFromDeck);
			}
		}
		Card chosenCard = _player.chooseACard(cardsFromWhichToChoose);
		
		if(chosenCard == null)
			return;
		
		cardsFromWhichToChoose.remove(chosenCard);
		_player.take(chosenCard);
		
		for(Card card:cardsFromWhichToChoose)
			card.discard();
		cardsFromWhichToChoose.clear(); 
	}
}
