package turn;

import message.event.CPhaseTransitionEvent;
import message.event.DoorKickEvent;
import message.event.FaceUpCurseCardDrawnEvent;
import player.Player;
import type.Types;
import card.CCurseCard;
import card.CDungeonCard;
import card.CMonsterCard;

public class COpenADoorPhase extends APhase {

	public COpenADoorPhase(CTurn turn) {
		super(turn);
	}

	protected void notifyStartOfPhase() {
		getGameEventNotifier().notifyListeners(new CPhaseTransitionEvent(Types.startOf, this));
	}

	protected void resolveAction(Player player) {
		CDungeonCard encounterCard = getTurn().getGame().getDeckManager().getDungeonDeck()
				.drawACard();

		getGameEventNotifier().notifyListeners(
				new DoorKickEvent(player, encounterCard));

		if (encounterCard instanceof CMonsterCard) {

			getTurn().setPlayerEncounteredAMonster(true);

			getTurn().getCombatMediator().setMonster((CMonsterCard) encounterCard);
			getTurn().getCombatMediator().play(player);

		} else if (encounterCard instanceof CCurseCard) {

			getGameEventNotifier().notifyListeners(
					new FaceUpCurseCardDrawnEvent(player));

			((CCurseCard) encounterCard).apply(player);

			encounterCard.discard();

		} else
			player.addCardToHand(encounterCard);
	}

	protected void notifyEndOfPhase() {
		getGameEventNotifier().notifyListeners(new CPhaseTransitionEvent(Types.endOf, this));
	}

	public String toString() {
		return ("Open A Door Phase");
	}
}
