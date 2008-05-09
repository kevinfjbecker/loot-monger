package message.command;

import player.Player;
import turn.CLookForTroublePhase;

public class PlayerDoesNotLookForTrouble implements IGameCommand {

	public final Player player;

	public final CLookForTroublePhase troublePhase;

	public PlayerDoesNotLookForTrouble(Player player,
			CLookForTroublePhase troublePhase) {
		this.player = player;
		this.troublePhase = troublePhase;
	}

	public void execute() {
		troublePhase.endWithoutTrouble();
	}

	public String toString() {
		return (player.getName() + " decides not to go looking for trouble.");
	}

}
