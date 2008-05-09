package message.command;

import player.Player;
import turn.CCombat;
import card.COneShotCard;

public class PlayerPlaysOneShotCardForMonsterSide implements IGameCommand {

	public final Player player;

	public final COneShotCard oneShotCard;

	public final CCombat combat;

	public PlayerPlaysOneShotCardForMonsterSide(Player player,
			COneShotCard oneShotCard, CCombat combat) {
		this.player = player;
		this.oneShotCard = oneShotCard;
		this.combat = combat;
	}

	public void execute() {
		player.getHand().remove(oneShotCard);
		combat.AddOneShotToMonsterSide(oneShotCard);
	}

	public String toString() {
		return (player.getName() + " plays " + oneShotCard + " for the monster's side.");
	}

}
