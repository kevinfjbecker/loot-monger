package palace.logical.card;

public class LogicalPlayer {

	private LogicalCardStack _cardStackOne = new LogicalCardStack();

	private LogicalCardStack _cardStackThree = new LogicalCardStack();

	private LogicalCardStack _cardStackTwo = new LogicalCardStack();

	private LogicalHand _hand = new LogicalHand();

	public LogicalPlayer() {
		_cardStackOne.setPlayer(this);
		_cardStackTwo.setPlayer(this);
		_cardStackThree.setPlayer(this);
		_hand.setPlayer(this);
	}

	public LogicalCardStack getCardStackOne() {
		return _cardStackOne;
	}

	public LogicalCardStack getCardStackThree() {
		return _cardStackThree;
	}

	public LogicalCardStack getCardStackTwo() {
		return _cardStackTwo;
	}

	public LogicalHand getHand() {
		return _hand;
	}

}
