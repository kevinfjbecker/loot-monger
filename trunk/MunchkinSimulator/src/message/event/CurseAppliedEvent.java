package message.event;

import card.CCurseCard;
import player.Player;

public class CurseAppliedEvent implements IGameEvent {

	public final Player player;

	public final CCurseCard card;

	public CurseAppliedEvent(Player player, CCurseCard card) {
		this.player = player;
		this.card = card;
	}

}
