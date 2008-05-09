package message.event;

import player.Player;

public class PlayerFirstTurnAfterDyingEvent implements IGameEvent {

	public final Player player;

	public PlayerFirstTurnAfterDyingEvent(Player player) {
		this.player = player;
	}

}
