package message.event;

import player.Player;

public class TakeTreasureFromSlainMonsterEvent implements IGameEvent {

	public final Player player;

	public final int monsterTreasures;

	public TakeTreasureFromSlainMonsterEvent(Player player,
			int monsterTreasures) {
		this.player = player;
		this.monsterTreasures = monsterTreasures;
	}

}
