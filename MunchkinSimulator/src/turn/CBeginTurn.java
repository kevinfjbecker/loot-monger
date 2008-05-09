package turn;

import message.event.CPhaseTransitionEvent;
import message.event.PlayerFirstTurnAfterDyingEvent;
import player.Player;
import type.Types;

public class CBeginTurn extends APhase {

	public CBeginTurn(CTurn turn) {
		super(turn);// TODO Auto-generated constructor stub
	}

	@Override
	protected void notifyStartOfPhase() {
		getGameEventNotifier().notifyListeners(new CPhaseTransitionEvent(Types.startOf, getTurn()));
	}

	@Override
	protected void resolveAction(Player player) {
		getTurn().setPlayer(player);
		getTurn().resetEventToggles();
		
		if (player.didDieLastTurn()) {
			getGameEventNotifier()
					.notifyListeners(new PlayerFirstTurnAfterDyingEvent(player));
			getTurn().getGame().drawTwoCardsFromEachDeck(player);
			player.resetDiedLastTurnToggle();
		}
		
		player.inform_equipItems();
	}

	@Override
	protected void notifyEndOfPhase() {
		// do nothing: "end of beginning" sounds dumb.
	}

	@Override
	public String toString() {
		return "Beginning of Turn";
	}

}
