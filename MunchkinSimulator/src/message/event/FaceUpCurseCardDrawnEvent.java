package message.event;

import player.Player;

public class FaceUpCurseCardDrawnEvent implements IGameEvent {

	public final Player player;

	public FaceUpCurseCardDrawnEvent(Player player) {
		this.player = player;
	}

}
