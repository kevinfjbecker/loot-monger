package message.event;

import player.Player;
import card.CDungeonCard;

public class DoorKickEvent extends Object implements IGameEvent {

	public final Player player;

	public final CDungeonCard encounterCard;

	public DoorKickEvent(Player player, CDungeonCard encounterCard) {
		this.player = player;
		this.encounterCard = encounterCard;
	}

}
