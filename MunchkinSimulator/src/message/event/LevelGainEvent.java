package message.event;

import player.Player;

public class LevelGainEvent implements IGameEvent {

	public final Player player;

	public final int levelGain;

	public LevelGainEvent(Player player, int levelGain) {
		this.player = player;
		this.levelGain = levelGain;
	}

}
