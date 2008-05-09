
package turn;

import message.event.CPhaseTransitionEvent;
import player.Player;
import type.Types;
import card.CMonsterCard;

public class CLookForTroublePhase extends APhase {

	public CLookForTroublePhase(CTurn turn) {
		super(turn);
	}

	protected void notifyStartOfPhase() {
		getGameEventNotifier()
		.notifyListeners(new CPhaseTransitionEvent(Types.startOf, this));

	}

	protected void resolveAction(Player player) {

		if(player.isAbleToLookForTrouble())
			player.inform_lookForTrouble(this);

	}
	
	public void endWithoutTrouble(){
		// do nothing =)
	}
	
	public void endWithTrouble(Player player, CMonsterCard monsterCard){
		getTurn().setDidPlayerEncounterAMonster(true);
		getTurn().getCombatMediator().setMonster(monsterCard);
		getTurn().getCombatMediator().play(player);
	}

	protected void notifyEndOfPhase() {
		getGameEventNotifier()
		.notifyListeners(new CPhaseTransitionEvent(Types.endOf, this));
	}

	public String toString() {
		return ("Look for Trouble Phase");
	}
	
}
