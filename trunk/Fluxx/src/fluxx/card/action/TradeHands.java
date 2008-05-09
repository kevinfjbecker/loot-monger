package fluxx.card.action;

import java.util.ArrayList;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.player.Player;

public class TradeHands extends Action {

	public TradeHands(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		Player victim = _player.chooseAPlayer(_game.getOtherPlayers(_player));
		if(victim == null)
			return;
		ArrayList<Card> swapList = new ArrayList<Card>();
		swapList.addAll(_player.getCardsInHand());
		_player.getCardsInHand().clear();
		_player.getCardsInHand().addAll(victim.getCardsInHand());
		victim.getCardsInHand().clear();
		victim.getCardsInHand().addAll(swapList);
	}

}
