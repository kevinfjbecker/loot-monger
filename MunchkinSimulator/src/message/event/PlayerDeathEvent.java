package message.event;

import player.Player;

public class PlayerDeathEvent implements IGameEvent {

	public final Player player;

	public PlayerDeathEvent(Player player) {
		this.player = player;
	}

}
