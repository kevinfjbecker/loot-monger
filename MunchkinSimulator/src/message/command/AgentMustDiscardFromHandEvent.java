package message.command;

import player.Agent;

public class AgentMustDiscardFromHandEvent implements IGameCommand {

	public final Agent agent;

	public final int numberOfCards;

	public AgentMustDiscardFromHandEvent(Agent agent,
			int numberOfCardsToDiscard) {
		this.agent = agent;
		this.numberOfCards = numberOfCardsToDiscard;
	}

	public void execute() {
		agent.discardCardsFromHand(numberOfCards);
	}
	
	public String toString(){
		return(agent.getName() + " must choose "
				+ numberOfCards + " cards to discard.");
	}

}
