package palace.logical.card;

import palace.visual.card.VisualCard;

public class LogicalCard implements Logical {

	public static enum Rank {
		Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace
	}

	public static enum Suit {
		Club, Diamond, Heart, Spade
	}

	private boolean _isFaceUp = false;

	private LogicalPlayArea _parent;

	private Rank _rank;

	private Suit _suit;

	private VisualCard _visualCard;

	public LogicalCard(Suit suit, Rank rank) {
		_suit = suit;
		_rank = rank;
	}

	public int getAlternativePointsValue() {
		switch (_rank) {
		case Ace:
			return 1;
		default:
			return getPointsValue();
		}
	}

	public LogicalPlayArea getParent() {
		return _parent;
	}

	public LogicalPlayer getPlayer() {
		return getParent().getPlayer();
	}

	public int getPointsValue() {
		switch (_rank) {
		case Two:
			return 2;
		case Three:
			return 3;
		case Four:
			return 4;
		case Five:
			return 5;
		case Six:
			return 6;
		case Seven:
			return 7;
		case Eight:
			return 8;
		case Nine:
			return 9;
		case Jack:
			return 10;
		case Queen:
			return 11;
		case King:
			return 12;
		case Ace:
			return 13;
		case Ten:
			return 15;
		default:
			return -1000000;
		}
	}

	public Rank getRank() {
		return _rank;
	}

	public Suit getSuit() {
		return _suit;
	}

	public VisualCard getVisualCard() {
		return _visualCard;
	}

	public boolean isFaceDown() {
		return !_isFaceUp;
	}

	public boolean isFaceUp() {
		return _isFaceUp;
	}

	public void setFaceDown() {
		_isFaceUp = false;
	}

	public void setFaceUp() {
		_isFaceUp = true;
	}

	public void setParent(LogicalPlayArea parent) {
		_parent = parent;
	}

	public void setVisualCard(VisualCard visualCard) {
		_visualCard = visualCard;
	}

	public String toString() {
		String s = "";
		switch (_rank) {
		case Two:
		case Three:
		case Four:
		case Five:
		case Six:
		case Seven:
		case Eight:
		case Nine:
		case Ten:
			s += getPointsValue();
			break;
		case Jack:
			s += 'J';
			break;
		case Queen:
			s += 'Q';
			break;
		case King:
			s += 'K';
			break;
		case Ace:
			s += 'A';
			break;
		}

		s += " of ";

		switch (_suit) {
		case Club:
			s += 'C';
			break;
		case Diamond:
			s += 'D';
			break;
		case Heart:
			s += 'H';
			break;
		case Spade:
			s += 'S';
			break;
		}

		s += "'s";

		return s;
	}

}
