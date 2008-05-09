package message.event;

import turn.CCombat;

public class PlayerKillsMonsterEvent implements IGameEvent {

	public final CCombat combat;

	public PlayerKillsMonsterEvent(CCombat combat) {
		this.combat = combat;
	}

}
