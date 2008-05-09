package fluxx.card.action;

import fluxx.FluxxGame;

public class DiscardAndDraw extends Action {

	public DiscardAndDraw(String name, FluxxGame game) {
		super(name, game);
	}

	@Override
	protected void doWhateverItSays() {
		int numberToDiscardAndDraw = _player.getCardsInHand().size();
		for(int i = numberToDiscardAndDraw ; i > 0 ; i--)
			_player.discardFromHand();
		
		for( int i = 0 ; i < numberToDiscardAndDraw ; i++)
			_player.drawOutOfTurn();
	}

}
