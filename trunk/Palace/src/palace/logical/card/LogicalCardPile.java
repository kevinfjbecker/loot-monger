package palace.logical.card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class LogicalCardPile extends Observable implements LogicalPlayArea {

	private ArrayList<LogicalCard> _cards = new ArrayList<LogicalCard>();

	public void add(LogicalCard card) {
		_cards.add(0, card);
		card.setParent(this);
		setChanged();
		notifyObservers(card);
	}

	public LogicalPlayer getPlayer() {
		return null;
	}

	public LogicalCard getTop() {
		if (!_cards.isEmpty())
			return _cards.get(0);
		return null;
	}

	public boolean isEmpty() {
		return _cards.isEmpty();
	}

	public Iterator<LogicalCard> iterator() {
		return _cards.iterator();
	}

	public void remove(Logical logical) {
		_cards.remove(logical);
		setChanged();
		notifyObservers();
	}

	public int size() {
		return _cards.size();
	}

	public String toString() {
		String s = "";
		s += super.toString();
		s += " [ ";
		for (LogicalCard card : _cards)
			s += card.toString() + " ";
		s += "]";
		return s;
	}

}
