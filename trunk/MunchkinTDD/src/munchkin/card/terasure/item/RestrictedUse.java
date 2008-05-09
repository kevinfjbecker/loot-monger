package munchkin.card.terasure.item;

import munchkin.player.Player;

public interface RestrictedUse {
	
	public boolean isUsableBy(Player player);
	
}
