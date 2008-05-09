package player;


import java.util.ArrayList;

import listener.GameEventNotifier;
import listener.GameObserver;

import card.CCard;
import card.CItemCard;

public class PlayerBuilder {
	
	private GameObserver _gameObserver;
	
	public PlayerBuilder(GameObserver gameObserver){
		setGameObserver(gameObserver);
	}
	
	public Player getPlayer(String playerName, String agentName) {
		
		Player player = new Player();
		Agent agent = new Agent(agentName);
		
		player.setName(playerName);
		player.setLevel(1);
		player.setAgent(agent);
		player.setHand(new ArrayList<CCard>());
		player.setCariedItems(new ArrayList<CItemCard>());
		player.setGameEventNotifier(new GameEventNotifier());
		player.getGameEventNotifier().addListener(getGameObserver());
		
		agent.setPlayer(player);
		agent.setGameEventNotifier(new GameEventNotifier());
		agent.getGameEventNotifier().addListener(getGameObserver());
		
		return player;
	}

	private void setGameObserver(GameObserver gameObserver) {
		_gameObserver = gameObserver;
	}

	private GameObserver getGameObserver() {
		return _gameObserver;
	}

}
