package munchkin.card.strategy;

import munchkin.player.Player;

public interface AlternativeConflictResolution {
	
	public boolean willAttack(Player player);

	public void seekAlternativeConflictResolution(Player player);
}
