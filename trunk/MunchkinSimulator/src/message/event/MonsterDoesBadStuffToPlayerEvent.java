package message.event;

import player.Player;

public class MonsterDoesBadStuffToPlayerEvent implements IGameEvent {

	public final Player player;

	public MonsterDoesBadStuffToPlayerEvent(Player player) {
		this.player = player;
	}

}
