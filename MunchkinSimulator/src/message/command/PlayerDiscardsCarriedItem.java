package message.command;

import player.Player;
import card.CCard;

public class PlayerDiscardsCarriedItem implements IGameCommand {

	public final Player player;

	public final CCard discard;

	public PlayerDiscardsCarriedItem(Player player,
			CCard discard) {
		this.player = player;
		this.discard = discard;
	}

	public void execute() {
		player.getHand().remove(discard);
		discard.discard();
	}
	
	public String toString(){
		return(player.getName() + " discards (carried item) " + discard + ".");
	}
}
