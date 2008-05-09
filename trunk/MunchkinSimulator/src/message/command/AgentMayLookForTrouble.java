package message.command;

import java.util.ArrayList;


import player.Agent;
import turn.CLookForTroublePhase;
import card.CMonsterCard;

public class AgentMayLookForTrouble implements IGameCommand {

	public final Agent agent;

	public final ArrayList<CMonsterCard> monsterCardsFromHand;

	public final CLookForTroublePhase troublePhase;

	public AgentMayLookForTrouble(Agent agent,
			ArrayList<CMonsterCard> monsterCardsFromHand,
			CLookForTroublePhase troublePhase) {
		this.agent = agent;
		this.monsterCardsFromHand = monsterCardsFromHand;
		this.troublePhase = troublePhase;
	}

	public void execute() {
		agent.lookForTrouble(monsterCardsFromHand, troublePhase);
	}

	public String toString() {
		return (agent.getName() + " may choose to look for trouble.");
	}

}
