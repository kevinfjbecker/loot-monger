package fluxx.card.action;

import java.util.ArrayList;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.player.Player;

public class EverybodyGetsOne extends Action {

	public EverybodyGetsOne(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		ArrayList<Card> cards = new ArrayList<Card>();
		Card cardTakenFromDeck = null;
		for(int i = 0 ; i < _game.getPlayers().size() ; i++) {
			cardTakenFromDeck = _game.getDeck().takeTopCard();
			if(cardTakenFromDeck != null)
				cards.add(cardTakenFromDeck);
		}
		ArrayList<Player> players = new ArrayList<Player>();
		players.addAll(_game.getPlayers());
		Player recipient = null;
		Card cardGivenToPlayer = null;
		for(int i = cards.size(); i > 0 ; i--) {
			cardGivenToPlayer = _player.chooseACard(cards);
			recipient = _player.chooseAPlayer(players);
			
			recipient.take(cardGivenToPlayer);
			
			cards.remove(cardGivenToPlayer);
			players.remove(recipient);
		}
	}

}
