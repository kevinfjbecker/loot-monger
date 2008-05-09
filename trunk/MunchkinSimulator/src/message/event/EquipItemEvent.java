package message.event;

import player.Player;
import card.CItemCard;

public class EquipItemEvent implements IGameEvent {

	public final Player player;

	public final CItemCard newEquipment;

	public EquipItemEvent(Player player, CItemCard newEquipment) {
		this.player = player;
		this.newEquipment = newEquipment;
	}

}
