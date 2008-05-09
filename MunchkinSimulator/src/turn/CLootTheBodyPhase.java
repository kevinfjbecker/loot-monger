package turn;

import java.util.ArrayList;
import java.util.Iterator;

import message.event.CPhaseTransitionEvent;

import player.CHighestToLowestWithRollOff;
import player.Player;
import type.Types;
import card.CCard;

public class CLootTheBodyPhase extends APhase {

	public CLootTheBodyPhase(CTurn turn) {
		super(turn);
	}

	protected void notifyStartOfPhase() {
		getGameEventNotifier().notifyListeners(
				new CPhaseTransitionEvent(Types.startOf, this));
	}

	protected void resolveAction(Player player) {

		ArrayList<CCard> lootCardList = new ArrayList<CCard>();

		lootCardList.addAll(player.getHand());
		lootCardList.addAll(player.getCarriedItems());
		player.getHand().clear();
		player.getCarriedItems().clear();

		for (Player p : getTurn().getGame().getPlayerManager())
			p.clearRollOffRolls();

		Player otherPlayer;
		for (Iterator<Player> i = getTurn().getGame().getPlayerManager()
				.iterator(new CHighestToLowestWithRollOff()); i.hasNext();) {
			
			if (lootCardList.isEmpty())
				break;
			
			otherPlayer = i.next();

			if (otherPlayer != player)
				otherPlayer.inform_collectLootCard(lootCardList);
		}

		for (CCard card : lootCardList)
			card.discard();

		lootCardList.clear();
	}

	protected void notifyEndOfPhase() {
		getGameEventNotifier().notifyListeners(
				new CPhaseTransitionEvent(Types.endOf, this));
	}

	public String toString() {
		return ("Loot the Body Phase");
	}
}
