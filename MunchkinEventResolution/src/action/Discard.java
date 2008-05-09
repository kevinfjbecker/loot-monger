
package action;

import gameobject.Card;
import gameobject.Player;

public class Discard implements Action {

	private Card _card;
	
	private Player _player;
	
	public Discard(Player player,Card card) {
		_card = card;
		_player = player;
	}

	public void execute() {
		_player.discard(_card);
	}

	public void undo() {
		_player.addToHand(_card);
	}

}
