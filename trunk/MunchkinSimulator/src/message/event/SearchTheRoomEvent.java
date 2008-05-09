package message.event;

import player.Player;

public class SearchTheRoomEvent implements IGameEvent {

	public final Player player;

	public SearchTheRoomEvent(Player player) {
		this.player = player;
	}

}
