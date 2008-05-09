package message.event;

import player.Player;

public class PlayerWinsGameEvent implements IGameEvent {

	public final Player player;

	public PlayerWinsGameEvent(Player player) {
		this.player = player;
	}
}
