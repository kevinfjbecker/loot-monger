package card;

import player.Player;

public class PlayerDeathBadStuff implements IDoesBadStuff {

	public void doBadStuff(Player player) {
		player.die();
	}
	
	public String toString(){
		return("bad stuff: death");
	}

}
