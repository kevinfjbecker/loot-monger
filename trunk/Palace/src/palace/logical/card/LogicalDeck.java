 package palace.logical.card;

import java.util.ArrayList;
import java.util.Collections;

import palace.visual.card.VisualDeck;


public class LogicalDeck {
	
	private ArrayList<LogicalCard> _cards = new ArrayList<LogicalCard>();

	private VisualDeck _visualDeck;

	public void add(LogicalCard logicalCard) {
		_cards.add(logicalCard);
	}

	public void clear() {
		_cards.clear();
	}

	public LogicalCard draw() {
		if (_cards.isEmpty())
			return null;
		return _cards.remove(0);
	}

	public VisualDeck getVisualDeck() {
		return _visualDeck;
	}

	public boolean isEmpty(){
		return _cards.isEmpty();
	}
	
	public void setVisualDeck(VisualDeck visualDeck) {
		_visualDeck = visualDeck;
	}

	public void shuffle() {
		Collections.shuffle(_cards);
	}

}
