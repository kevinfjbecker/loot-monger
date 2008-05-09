package message.command;

import java.util.ArrayList;


import player.Agent;
import card.CCard;

public class AgentMustDecideOnALootCard implements IGameCommand {

	public final Agent agent;
	
	public ArrayList<CCard> lootCardList;

	public AgentMustDecideOnALootCard(Agent agent, ArrayList<CCard> lootCardList) {
		this.agent = agent;
		this.lootCardList = lootCardList;
	}

	public void execute() {
		agent.selectLootCard(lootCardList);
	}
	
	public String toString(){
		return(agent.getName() + " must choose a card from the slain player.");
	}

}
