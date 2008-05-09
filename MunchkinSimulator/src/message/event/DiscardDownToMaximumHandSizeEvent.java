package message.event;

import player.Player;

public class DiscardDownToMaximumHandSizeEvent implements IGameEvent {

	public final Player player;

	public final int numberToDiscard;

	public DiscardDownToMaximumHandSizeEvent(Player player,
			int numberToDiscard) {
		this.player = player;
		this.numberToDiscard = numberToDiscard;
	}

}
