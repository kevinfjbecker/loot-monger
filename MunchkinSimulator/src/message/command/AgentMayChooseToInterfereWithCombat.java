
package message.command;

import java.util.ArrayList;


import player.Agent;
import turn.CCombat;
import card.COneShotCard;

public class AgentMayChooseToInterfereWithCombat implements IGameCommand {

	public final Agent agent;
	public final ArrayList<COneShotCard> oneShotCards;
	public final CCombat combat;
	
	public AgentMayChooseToInterfereWithCombat(Agent agent, ArrayList<COneShotCard> oneShotCards, CCombat combat) {
		this.agent = agent;
		this.oneShotCards = oneShotCards;
		this.combat = combat;
	}

	public void execute() {
		agent.interfereWithCombat(oneShotCards,combat);
	}
	
	public String toString(){
		return(agent.getName()+" may interfere with combat.");
	}

}
