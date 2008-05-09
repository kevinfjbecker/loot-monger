
package turn;

import message.event.CPhaseTransitionEvent;
import message.event.SearchTheRoomEvent;
import player.Player;
import type.Types;

public class CLootTheRoomPhase extends APhase {

	public CLootTheRoomPhase(CTurn turn) {
		super(turn);
	}

	protected void notifyStartOfPhase() {
		getGameEventNotifier().notifyListeners(new CPhaseTransitionEvent(Types.startOf, this));
	}

	protected void resolveAction(Player player) {
		getGameEventNotifier().notifyListeners(new SearchTheRoomEvent(player));
		
		player.drawACard(getTurn().getGame().getDeckManager().getDungeonDeck());
	}

	protected void notifyEndOfPhase() {
		getGameEventNotifier().notifyListeners(new CPhaseTransitionEvent(Types.endOf, this));
	}
	
	public String toString() {
		return ("Loot the Room Phase");
	}

}
