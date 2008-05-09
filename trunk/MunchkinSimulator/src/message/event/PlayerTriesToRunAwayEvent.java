package message.event;

import turn.CCombat;

public class PlayerTriesToRunAwayEvent implements IGameEvent {

	public final CCombat combat;

	public PlayerTriesToRunAwayEvent(CCombat combat) {
		this.combat = combat;
	}

}
