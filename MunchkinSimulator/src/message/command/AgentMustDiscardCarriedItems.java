package message.command;

import player.Agent;

public class AgentMustDiscardCarriedItems implements IGameCommand {

	public final Agent agent;

	public final int numberOfCards;

	public AgentMustDiscardCarriedItems(Agent agent,
			int numberOfCardsToDiscard) {
		this.agent = agent;
		this.numberOfCards = numberOfCardsToDiscard;
	}

	public void execute() {
		agent.discardCarriedItems(numberOfCards);
	}

	public String toString() {
		return (agent.getName() + " must choose " + numberOfCards + " carried items to discard.");
	}

}
