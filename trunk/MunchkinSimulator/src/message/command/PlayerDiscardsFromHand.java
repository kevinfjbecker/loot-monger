package message.command;

import player.Player;
import card.CCard;

public class PlayerDiscardsFromHand implements IGameCommand {

	public final Player player;

	public final CCard discard;

	public PlayerDiscardsFromHand(Player player, CCard discard) {
		this.player = player;
		this.discard = discard;
	}

	public void execute() {
		player.getHand().remove(discard);
		discard.discard();
	}

	public String toString() {
		return (player.getName() + " discards (from hand) " + discard + ".");
	}

}
