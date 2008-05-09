package turn;

import java.util.Iterator;

public class CHackIterator implements Iterator<IPhase> {

	private CTurn _turn;
	
	public CHackIterator(CTurn turn) {
		_turn = turn;
	}

	public boolean hasNext() {
		return _turn.hasNext();
	}

	public IPhase next() {
		return _turn.getNext();
	}

	public void remove() {
		// not implemented
	}

}
