package message.event;

import player.Player;

public class LevelLossEvent implements IGameEvent {

	public final Player player;

	public final int levelLoss;

	public final int netLevelLoss;

	public LevelLossEvent(Player player, int levelLoss, int netLevelLoss) {
		this.player = player;
		this.levelLoss = levelLoss;
		this.netLevelLoss = netLevelLoss;
	}

}
