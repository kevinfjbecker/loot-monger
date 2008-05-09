package turn;

import message.event.CPhaseTransitionEvent;
import message.event.DiscardDownToMaximumHandSizeEvent;
import player.Player;
import type.Types;

public class CCharityPhase extends APhase {

	public CCharityPhase(CTurn turn) {
		super(turn);
	}

	protected void notifyStartOfPhase() {
		getGameEventNotifier().notifyListeners(new CPhaseTransitionEvent(Types.startOf, this));
	}

	protected void resolveAction(Player player) {
		
		getGameEventNotifier().notifyListeners(
				new DiscardDownToMaximumHandSizeEvent(player, player
						.numberOfCardsOverMaxHandSize()));

		player.inform_discardCardsFromHand(player
				.numberOfCardsOverMaxHandSize());
	}

	protected void notifyEndOfPhase() {
		getGameEventNotifier().notifyListeners(new CPhaseTransitionEvent(Types.endOf, this));
	}
	
	public String toString() {
		return ("Charity Phase");
	}
}
