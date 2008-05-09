
package player;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import listener.GameObserver;

public class PlayerManager implements Iterable<Player>{

	private PlayerBuilder _playerBuilder;
	private ArrayList<Player> _playerList;
	
	public PlayerManager(GameObserver gameObserver){
		setPlayerBuilder(new PlayerBuilder(gameObserver));
		setPlayerList(new ArrayList<Player>());
	}
	
	public void addPlayer(String playerName, String agentName) {
		getPlayerList().add(getPlayerBuilder().getPlayer(playerName,
				agentName));
	}
	
	public int getNumberOfCardsHeldByPlayers() {
		int cardsInGame = 0;
		for (int i = 0; i < getPlayerList().size(); i++) {
			cardsInGame += getPlayerList().get(i).numberOfCardsInHand();
			cardsInGame += getPlayerList().get(i).getCarriedItems().size();
		}
		return (cardsInGame);
	}
	
	///////////////////////////////////////////////////////////////////////////

	public Iterator<Player> iterator() {
		return (getPlayerList().iterator());
	}
	
	public Iterator<Player> iterator(Comparator<Player> c){
		ArrayList<Player> l;
		l = new ArrayList<Player>(getPlayerList());
		Collections.sort(l,c);
		
		return(l.iterator());
	}

	private void setPlayerBuilder(PlayerBuilder playerBuilder) {
		_playerBuilder = playerBuilder;
	}

	private PlayerBuilder getPlayerBuilder() {
		return (_playerBuilder);
	}

	private void setPlayerList(ArrayList<Player> playerList) {
		_playerList = playerList;
	}

	private ArrayList<Player> getPlayerList() {
		return (_playerList);
	}
	
}
