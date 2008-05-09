package turn;

import message.event.CPhaseTransitionEvent;
import player.Player;
import type.Types;

public class CEndTurn extends APhase {

	public CEndTurn(CTurn turn) {
		super(turn);
	}

	@Override
	protected void notifyStartOfPhase() {
	}

	@Override
	protected void resolveAction(Player player) {
		getTurn().clearPlayer();
	}

	@Override
	protected void notifyEndOfPhase() {
		getGameEventNotifier().notifyListeners(new CPhaseTransitionEvent(Types.endOf, getTurn()));
	}

	public String toString(){
		return "End of Turn";
	}
	
}
