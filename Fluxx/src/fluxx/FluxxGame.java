package fluxx;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import fluxx.card.Card;
import fluxx.card.goal.Goal;
import fluxx.card.newRule.NewRule;
import fluxx.player.Agent;
import fluxx.player.Player;
import fluxx.player.RandomAgent;
import fluxx.rule.Rule;
import fluxx.rule.draw.BasicDrawRule;
import fluxx.rule.limit.NoHandLimitRule;
import fluxx.rule.limit.NoKeeperLimitRule;
import fluxx.rule.play.BasicPlayRule;
import fluxx.rule.play.PlayRule;

// TODO: don't really want this to be a Thread
public class FluxxGame extends Thread {

	private static HashMap<String, Integer> goalFrequency;

	static {
		goalFrequency = new HashMap<String, Integer>();
	}

	private ArrayList<Integer> _cardsDrawnPerTurn;

	private ArrayList<Integer> _cardsPlayedPerTurn;

	private Player _currentPlayer;

	private Deck _deck;

	private ArrayList<Card> _discardPile;

	private Goal _goal;

	private ArrayList<NewRule> _newRules;

	private int _numberOfCardsDrawn;

	private int _numberOfCardsPlayed;

	private PrintStream _out;

	private ArrayList<Player> _players;

	private ArrayList<Rule> _turn;

	public FluxxGame(PrintStream out) {
		_out = out;
		_discardPile = new ArrayList<Card>();
		_deck = new Deck(this);
		_turn = new ArrayList<Rule>();
		_turn.add(new BasicDrawRule(this));
		_turn.add(new BasicPlayRule(this));
		_turn.add(new NoHandLimitRule(this));
		_turn.add(new NoKeeperLimitRule(this));
		_players = new ArrayList<Player>();
		_newRules = new ArrayList<NewRule>();
		_cardsDrawnPerTurn = new ArrayList<Integer>();
		_cardsPlayedPerTurn = new ArrayList<Integer>();
	}

	public void addPlayer(String playerName, Agent agent) {
		Player player = new Player(playerName, this, _out);
		player.setAgent(agent);
		agent.setPlayer(player);
		agent.setGame(this);
		_players.add(player);
	}

	private String dashedLine() {
		String s = "";
		for (int i = 0; i < 80; i++)
			s+='-';
		return s;
	}

	private void enterEndingGoalIntoGoalDistibution() {
		if (goalFrequency.containsKey(_goal.toString())) {
			goalFrequency.put(_goal.toString(), goalFrequency.get(
					_goal.toString()).intValue() + 1);
		} else {
			goalFrequency.put(_goal.toString(), new Integer(1));
		}
	}

	public ArrayList<Card> getAllTheCards() {
		ArrayList<Card> allTheCards = new ArrayList<Card>();
		allTheCards.addAll(_newRules);
		allTheCards.addAll(_deck.getCards());
		allTheCards.addAll(_discardPile);
		for (Player player : _players) {
			allTheCards.addAll(player.getCardsInHand());
			allTheCards.addAll(player.getKeepersInPlay());
		}
		if (_goal != null)
			allTheCards.add(_goal);
		return (allTheCards);
	}

	public int getCardTotal() {
		return getAllTheCards().size();
	}

	public Player getCurrentPlayer() {
		return _currentPlayer;
	}

	public Deck getDeck() {
		return _deck;
	}

	public ArrayList<Card> getDiscardPile() {
		return _discardPile;
	}

	public Goal getGoal() {
		return _goal;
	}

	public ArrayList<NewRule> getNewRules() {
		return _newRules;
	}

	public int getNumberOfCardsDrawn() {
		return _numberOfCardsDrawn;
	}

	public int getNumberOfCardsPlayed() {
		return _numberOfCardsPlayed;
	}

	public ArrayList<Player> getOtherPlayers(Player player) {
		ArrayList<Player> otherPlayers = new ArrayList<Player>();
		otherPlayers.addAll(_players);
		otherPlayers.remove(player);
		return otherPlayers;
	}

	public ArrayList<Player> getPlayers() {
		return _players;
	}

	public PlayRule getPlayRule() {
		for (Rule rule : _turn)
			if (rule instanceof PlayRule)
				return (PlayRule) rule;
		return null;
	}

	public ArrayList<Rule> getTurn() {
		return _turn;
	}

	public void play() {

		openingDraw();

		while (_goal == null || _goal.checkForWinner() == null) {
			for (Player player : _players) {
				/*
				 * The game ends immediatly, if the Goal conditions are met.
				 */
				if (_goal != null && _goal.checkForWinner() != null)
					break;
				playTurn(player);
			}
		}
		enterEndingGoalIntoGoalDistibution();
	}

	private void openingDraw() {

		for (Player player : _players)
			for (int i = 0; i < 3; i++)
				player.draw();

		if (_out != null) 
			_out.println(dashedLine());
		
	}

	public void playTurn(Player player) {
		setCurrentPlayer(player);
		setNumberOfCardsDrawn(0);
		setNumberOfCardsPlayed(0);
		for (int i = 0; i < _turn.size(); i++) {
			/*
			 * The game ends immediatly, if the Goal conditions are met.
			 */
			if (_goal != null && _goal.checkForWinner() != null)
				break;
			_turn.get(i).follow(player);
		}
		_cardsDrawnPerTurn.add(getNumberOfCardsDrawn());
		_cardsPlayedPerTurn.add(getNumberOfCardsPlayed());

		if (_out != null) {
			_out.println(dashedLine());
			_out.println(this.toString());
			_out.println(dashedLine());
		}
		
		// TODO: don't really want the game to sleep
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		play();
	}

	private void setCurrentPlayer(Player player) {
		_currentPlayer = player;
	}

	public void setGoal(Goal goal) {
		_goal = goal;
	}

	public void setNumberOfCardsDrawn(int i) {
		_numberOfCardsDrawn = i;
	}

	public void setNumberOfCardsPlayed(int i) {
		_numberOfCardsPlayed = i;
	}

	public void setUpRandomTestPlayers() {
		for (int i = 0; i < 6; i++) {
			addPlayer("Player" + i, new RandomAgent());
		}
	}

	public String showCardsDrawnAndPlayedPerTurn() {
		String s = "";
		for (int i = 0; i < _cardsDrawnPerTurn.size(); i++) {
			for (int k = 0; k < _cardsDrawnPerTurn.get(i); k++) {
				s += '|';
			}
			s += " Cards Drawn\t";
			s += _cardsDrawnPerTurn.get(i) < 4 ? "\t" : "";
			for (int k = 0; k < _cardsPlayedPerTurn.get(i); k++) {
				s += '|';
			}
			s += " Cards Played\n";
		}
		return s;
	}

	public String showCardTotalBreakdown() {
		String s = "";
		s += "New Rules:\t" + getNewRules().size() + '\n';
		s += "Deck:\t\t" + getDeck().getNumberOfCardsInDeck() + '\n';
		s += "Discard Pile:\t" + getDiscardPile().size() + '\n';
		for (Player player : _players) {
			s += "Player Hand:\t" + player.getCardsInHand().size() + '\n';
			s += "Player Keepers:\t" + player.getKeepersInPlay().size() + '\n';
		}
		s += "Goals:\t\t" + (getGoal() == null ? 0 : 1) + '\n';
		return s;
	}

	public String showEndingGoalDisributionOverGamesPlayed() {
		String s = "";
		s += "Occurences Of Goals at Game End\n";
		s += "-------------------------------\n";
		for (String name : goalFrequency.keySet()) {
			for (int i = 0; i < goalFrequency.get(name); i += 10)
				s += '|';
			s += name + ": " + goalFrequency.get(name) + "\n";
		}
		return s;
	}

	public String toString() {
		String s = "";
		s += "Goal: " + _goal + '\n';
		s += "New Rules" + _newRules;
		for (Player player : _players) {
			s += '\n';
			s += player;
		}
		s += "\nCards in Deck: " + _deck.getNumberOfCardsInDeck() + '\n';
		s += "Card in Discard Pile: " + _discardPile.size();
		return s;
	}

}
