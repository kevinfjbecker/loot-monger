package palace.logical.card;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import logical.State;
import palace.logical.phase.ChanceFailedPhase;
import palace.logical.phase.EndPlayOrEatPhase;
import palace.logical.phase.MainDrawBackUpPhase;
import palace.logical.phase.MainPlayOrEatPhase;
import palace.logical.phase.Phase;
import palace.logical.phase.SetupSixFaceUpPhase;
import palace.logical.phase.SetupThreeFaceDownPhase;
import palace.logical.phase.SetupThreeOpenPlayPhase;

public class GameState implements ActionListener, State {

	public static final int SETUP_THREE_FACE_DOWN = 0;

	public static final int SETUP_SIX_FACE_UP = 1;

	public static final int SETUP_THREE_OPEN_PLAY = 2;

	public static final int MAIN_PLAY_OR_EAT = 3;

	public static final int MAIN_DRAW_BACK_UP = 4;

	public static final int END_PLAY_OR_EAT = 5;
	
	public static final int CHANCE_FAILED = 6;

	private LogicalPlayer _currentPlayer;

	private LogicalDeck _deck = new LogicalDeck();

	private Phase _endPlayOrEat = new EndPlayOrEatPhase();

	private boolean _lastMoveValid = false;

	private Phase _mainDrawBackUpPhase = new MainDrawBackUpPhase();

	private Phase _mainPlayOrEatPhase = new MainPlayOrEatPhase();

	private LogicalPlayer _otherPlayer = new LogicalPlayer();

	private LogicalCardPile _pile = new LogicalCardPile();

	private LogicalPlayer _player = new LogicalPlayer();

	private Phase _setupSixFaceUpPhase = new SetupSixFaceUpPhase();

	private Phase _setupThreeFaceDownPhase = new SetupThreeFaceDownPhase();

	private Phase _chanceFailedPhase = new ChanceFailedPhase();

	private Phase _setupThreeOpenPlayPhase = new SetupThreeOpenPlayPhase();

	private int _state;

	public GameState() {
		_currentPlayer = _player;
		CardFactory.populate(_deck);
		_setupThreeFaceDownPhase.setGameState(this);
		_setupSixFaceUpPhase.setGameState(this);
		_setupThreeOpenPlayPhase.setGameState(this);
		_mainPlayOrEatPhase.setGameState(this);
		_mainDrawBackUpPhase.setGameState(this);
		_endPlayOrEat.setGameState(this);
		_chanceFailedPhase.setGameState(this);
	}

	public void actionPerformed(ActionEvent actionEvent) {
		switch (_state) {
		case SETUP_THREE_FACE_DOWN:
			_setupThreeFaceDownPhase.actionPerformed(actionEvent);
			break;
		case SETUP_SIX_FACE_UP:
			_setupSixFaceUpPhase.actionPerformed(actionEvent);
			break;
		case SETUP_THREE_OPEN_PLAY:
			_setupThreeOpenPlayPhase.actionPerformed(actionEvent);
			break;
		case MAIN_PLAY_OR_EAT:
			_mainPlayOrEatPhase.actionPerformed(actionEvent);
			break;
		case MAIN_DRAW_BACK_UP:
			_mainDrawBackUpPhase.actionPerformed(actionEvent);
			break;
		case END_PLAY_OR_EAT:
			_endPlayOrEat.actionPerformed(actionEvent);
			break;
		case CHANCE_FAILED:
			_chanceFailedPhase.actionPerformed(actionEvent);
			break;
		}
	}

	public LogicalPlayer getCurrentPlayer() {
		return _currentPlayer;
	}

	public LogicalDeck getDeck() {
		return _deck;
	}

	public ArrayList<LogicalPlayer> getEachOfThePlayers() {
		ArrayList<LogicalPlayer> players = new ArrayList<LogicalPlayer>();
		players.add(_player);
		players.add(_otherPlayer);
		return players;
	}

	public LogicalPlayer getOtherPlayer() {
		return _otherPlayer;
	}

	public LogicalCardPile getPile() {
		return _pile;
	}

	public LogicalPlayer getPlayer() {
		return _player;
	}

	public void moveOnToNextPlayer() {
		setCurrentPlayer(_currentPlayer == _player ? _otherPlayer : _player);
	}

	public void setCurrentPlayer(LogicalPlayer logicalPlayer) {
		_currentPlayer = logicalPlayer;
	}

	public void setLastMoveValid(boolean lastMoveValid) {
		_lastMoveValid = lastMoveValid;
	}

	public void setState(int state) {
		_state = state;
	}

	public boolean wasLastMoveValid() {
		return _lastMoveValid;
	}

}
