package message.event;

import player.Player;

public class DrawTwoCardsFromEachDeckEvent implements IGameEvent {

	public final Player player;

	public DrawTwoCardsFromEachDeckEvent(Player player) {
		this.player = player;
	}

}
