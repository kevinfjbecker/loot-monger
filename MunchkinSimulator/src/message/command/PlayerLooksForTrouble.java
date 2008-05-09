package message.command;

import player.Player;
import turn.CLookForTroublePhase;
import card.CMonsterCard;

public class PlayerLooksForTrouble implements IGameCommand {

	public final Player player;

	public final CMonsterCard monster;

	public final CLookForTroublePhase troublePhase;

	public PlayerLooksForTrouble(Player player,
			CMonsterCard monster, CLookForTroublePhase troublePhase) {
		this.player = player;
		this.monster = monster;
		this.troublePhase = troublePhase;
	}

	public void execute() {
		troublePhase.endWithTrouble(player,monster);
	}

	public String toString() {
		return (player.getName() + " goes looking for trouble with " + monster + "...");
	}

}
