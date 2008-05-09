package message.command;

import java.util.ArrayList;


import player.Agent;
import turn.CCombat;
import card.COneShotCard;

public class AgentMayChooseToPlayOneShot implements IGameCommand {

	public final Agent agent;

	public final ArrayList<COneShotCard> oneShotCards;

	public final CCombat combat;

	public AgentMayChooseToPlayOneShot(Agent agent,
			ArrayList<COneShotCard> oneShotCards, CCombat combat) {
		this.agent = agent;
		this.oneShotCards = oneShotCards;
		this.combat = combat;
	}

	public void execute() {
		agent.playOneShotCards(oneShotCards, combat);
	}

	public String toString() {
		return (agent.getName() + " may choose to play a one-shot card.");
	}

}
