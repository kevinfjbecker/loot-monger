package message.command;

import player.Player;
import turn.CCombat;
import card.COneShotCard;

public class PlayerPlaysOneShotCardForAdventurerSide implements IGameCommand {

	public final Player player;

	public final COneShotCard oneShotCard;

	public final CCombat combat;

	public PlayerPlaysOneShotCardForAdventurerSide(Player player,
			COneShotCard oneShotCard, CCombat combat) {
		this.player = player;
		this.oneShotCard = oneShotCard;
		this.combat = combat;
	}

	public void execute() {
		player.getHand().remove(oneShotCard);
		combat.AddOneShotToAventurerSide(oneShotCard);
	}

	public String toString() {
		return (player.getName() + " plays " + oneShotCard + " for the adventurer's side.");
	}

}
