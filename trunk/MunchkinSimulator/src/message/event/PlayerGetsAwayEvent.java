package message.event;

import player.Player;

public class PlayerGetsAwayEvent implements IGameEvent {

	public final Player player;

	public PlayerGetsAwayEvent(Player player) {
		this.player = player;
	}

}
