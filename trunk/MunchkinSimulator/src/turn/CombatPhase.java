package turn;

import message.event.CPhaseTransitionEvent;
import message.event.MonsterDoesBadStuffToPlayerEvent;
import message.event.PlayerGetsAwayEvent;
import message.event.PlayerKillsMonsterEvent;
import message.event.PlayerTriesToRunAwayEvent;
import message.event.TakeTreasureFromSlainMonsterEvent;
import player.Player;
import type.Types;
import card.CMonsterCard;

public class CombatPhase extends APhase {

	private CMonsterCard monster;

	public CombatPhase(CTurn turn) {
		super(turn);
	}

	public void setMonster(CMonsterCard monster) {
		this.monster = monster;
	}

	private void stealTheTreasure(Player player, CMonsterCard monster) {
		getGameEventNotifier().notifyListeners(
				new TakeTreasureFromSlainMonsterEvent(player, monster
						.getNumberOfTreasures()));

		for (int i = 0; i < monster.getNumberOfTreasures(); i++)
			player.drawACard(getTurn().getGame().getDeckManager()
					.getTreasureDeck());
	}

	protected void notifyStartOfPhase() {
		getGameEventNotifier().notifyListeners(
				new CPhaseTransitionEvent(Types.startOf, this));
	}

	protected void resolveAction(Player player) {
		CCombat combat = new CCombat(player, monster);

		do {
			combat.resetNewCardsWerePlayedToggle();
			player.inform_playOneShotCards(combat);

			for (Player otherPlayer : getTurn().getGame()
					.getPlayerManager()) {
				if (otherPlayer != player)
					otherPlayer.inform_interfereWithCombat(combat);
			}

		} while (combat.newCardsWerePlayered());
		if (combat.adventurerSideDefeatsMonsters()) {
			getGameEventNotifier().notifyListeners(
					new PlayerKillsMonsterEvent(combat));
			player.gainLevels(1);

			stealTheTreasure(player, monster);

		} else {
			getGameEventNotifier().notifyListeners(
					new PlayerTriesToRunAwayEvent(combat));
			if (!player.runAway(monster)) {
				getGameEventNotifier().notifyListeners(
						new MonsterDoesBadStuffToPlayerEvent(player));
				monster.doBadStuff(player);
				if (player.didDieLastTurn())
					getTurn().getLootTheBodyPhase().play(player);
			} else {
				getGameEventNotifier().notifyListeners(
						new PlayerGetsAwayEvent(player));
			}
		}
		combat.discardCards();
		this.setMonster(null);
	}

	protected void notifyEndOfPhase() {
		getGameEventNotifier().notifyListeners(
				new CPhaseTransitionEvent(Types.endOf, this));
	}

	public String toString() {
		return ("Combat Phase");
	}
}
