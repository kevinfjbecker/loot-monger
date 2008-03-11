package palace.logical.card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class LogicalCardStack extends Observable implements LogicalPlayArea {

	private ArrayList<LogicalCard> _cards = new ArrayList<LogicalCard>();
	
	private LogicalPlayer _player;

	public void add(LogicalCard card) {
		_cards.add(0, card);
		card.setParent(this);
		setChanged();
		notifyObservers(card);
	}

	public LogicalPlayer getPlayer() {
		return _player;
	}

	public Iterator<LogicalCard> iterator() {
		return _cards.iterator();
	}

	public void remove(Logical logical) {
		_cards.remove(logical);
	}
	
	public void setPlayer(LogicalPlayer player){
		_player = player;
	}
	
	public int size() {
		return _cards.size();
	}
	
	public LogicalCard top(){
		return _cards.get(0);
	}
	
	public String toString(){
		String s = "";
		s+=super.toString();
		s+=" [ ";
		for(LogicalCard card:_cards)
			s+=card.toString()+" ";
		s+="]";
		return s;
	}



}
