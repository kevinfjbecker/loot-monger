package fluxx.player;

import java.io.PrintStream;
import java.util.ArrayList;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.card.action.RotateHands.Direction;

public class Player {

	private static final boolean _countsAsOutOfTurn = true;

	private static final boolean _countsAsInTurn = false;

	private Agent _agent;

	private ArrayList<Card> _cardsInHand;

	private FluxxGame _game;

	private ArrayList<Card> _keepersInPlay;

	private String _name;

	private PrintStream _out;

	public Player(String name, FluxxGame game, PrintStream out) {
		_out = out;
		_name = name;
		_game = game;
		_cardsInHand = new ArrayList<Card>();
		_keepersInPlay = new ArrayList<Card>();
	}

	public Card chooseACard(ArrayList<? extends Card> cards) {
		if (cards.isEmpty())
			return null;
		return _agent.chooseACard(cards);
	}

	public Player chooseAPlayer(ArrayList<Player> players) {
		if (players.isEmpty())
			return null;
		return _agent.chooseAPlayer(players);
	}

	public Direction chooseLeftOrRight() {
		return _agent.chooseLeftOrRight();
	}

	public boolean chooseYesOrNo() {
		return _agent.chooseYesOrNo();
	}

	public void discardFromHand() {

		Card card = chooseACard(_cardsInHand);

		if (_out != null)
			_out.println(_name + " discards " + card + " from cards in hand.");

		_cardsInHand.remove(card);
		card.discard();

	}

	public void discardFromKeepersInPlay() {
		Card card = chooseACard(_keepersInPlay);
		discardFromKeepersInPlay(card);
	}

	public void discardFromKeepersInPlay(Card card) {
		
		if (_out != null)
			_out
					.println(_name + " discards " + card
							+ " from Keepers in play.");
		
		_keepersInPlay.remove(_keepersInPlay.indexOf(card));
		card.discard();
	}

	private void doDrawAction(boolean isOutOfTurn, ArrayList<Card> destination) {
		Card card = _game.getDeck().takeTopCard();
		if (card != null) {
			if (!isOutOfTurn)
				_game.setNumberOfCardsDrawn(_game.getNumberOfCardsDrawn() + 1);

			if (_out != null)
				_out.println(_name + " draws " + card + ".");

			destination.add(card);
			card.setPlayer(this);

		} else if (_out != null)
			_out.println(_name + " misses a draw.");
	}

	public void draw() {
		doDrawAction(_countsAsInTurn, _cardsInHand);
	}

	public void drawOutOfTurn() {
		doDrawAction(_countsAsOutOfTurn, _cardsInHand);
	}

	public void drawOutOfTurn(ArrayList<Card> cards) {
		doDrawAction(_countsAsOutOfTurn, cards);
	}

	public ArrayList<Card> getCardsInHand() {
		return _cardsInHand;
	}

	public ArrayList<Card> getKeepersInPlay() {
		return _keepersInPlay;
	}

	public String getName() {
		return _name;
	}

	public void playCard() {
		if (_cardsInHand.size() == 0)
			return;
		playCard(_countsAsInTurn, chooseACard(_cardsInHand), _cardsInHand);
	}

	public void playCard(boolean isOutOfTurn, Card card,
			ArrayList<Card> location) {

		if (!isOutOfTurn)
			_game.setNumberOfCardsPlayed(_game.getNumberOfCardsPlayed() + 1);

		location.remove(card);

		if (_out != null)
			_out.println(_name + " plays " + card + ".");

		card.play();
	}

	public void playCardAtRandom() {
		playCard(_countsAsInTurn, _cardsInHand
				.get((int) (Math.random() * _cardsInHand.size())), _cardsInHand);
	}

	public void playCardOutOfTurn(Card card, ArrayList<Card> location) {
		playCard(_countsAsOutOfTurn, card, location);
	}

	public void setAgent(Agent agent) {
		_agent = agent;
	}

	public void take(Card card) {
		card.setPlayer(this);
		_cardsInHand.add(card);
	}

	public String toString() {
		String s = _name + '\n';
		s += "\tHand: " + _cardsInHand + "\n";
		s += "\tIn Play: " + _keepersInPlay;
		return s;
	}

}
