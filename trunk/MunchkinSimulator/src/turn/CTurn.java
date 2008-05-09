package turn;

import game.Game;

import java.util.Iterator;

import listener.GameEventNotifier;
import listener.GameObserver;
import player.Player;

public class CTurn implements IPhase, ITurnStructure {

	private CBeginTurn beginTurn;

	private COpenADoorPhase openADoor;

	private CCharityPhase charity;

	private CLookForTroublePhase lookForTrouble;

	private CLootTheRoomPhase lootTheRoom;

	private CLootTheBodyPhase lootTheBodyPhase;

	private CombatPhase combatPhase;

	private boolean playerEncounteredAMonster;

	private Player _player;

	private Game _game;

	private GameEventNotifier gameEventNotifier;

	private GameObserver _gameObserver;

	public enum PhaseMarker {
		begin, door, trouble, loot, discard, end, done
	};

	private PhaseMarker nextPhase;

	private CEndTurn endTurn;

	public CTurn(Game game) {

		gameEventNotifier = new GameEventNotifier();

		setGame(game);

		_gameObserver = game.getGameObserver();
		getGameEventNotifier().addListener(game.getGameObserver());

		beginTurn = new CBeginTurn(this);
		endTurn = new CEndTurn(this);

		openADoor = new COpenADoorPhase(this);
		charity = new CCharityPhase(this);
		lookForTrouble = new CLookForTroublePhase(this);
		lootTheRoom = new CLootTheRoomPhase(this);

		lootTheBodyPhase = new CLootTheBodyPhase(this);
		combatPhase = new CombatPhase(this);
	}

	///////////////////////////////////////////////////////////////////////////

	public void play(Player player) {

		beginTurn.play(player);

		player.inform_equipItems();

		openADoor.play(player);

		if (!playerEncounteredAMonster) {
			lookForTrouble.play(player);
		}

		if (!playerEncounteredAMonster) {
			lootTheRoom.play(player);
		}

		if (player.numberOfCardsInHand() > player.getMaxHandSize()) {
			charity.play(player);
		}

		endTurn.play(player);
	}

	///////////////////////////////////////////////////////////////////////////

	public void resetEventToggles() {
		playerEncounteredAMonster = false;
	}

	///////////////////////////////////////////////////////////////////////////

	public CombatPhase getCombatMediator() {
		return (combatPhase);
	}

	public GameObserver getGameObserver() {
		return _gameObserver;
	}

	public Game getGame() {
		return (_game);
	}

	public CLootTheBodyPhase getLootTheBodyPhase() {
		return (lootTheBodyPhase);
	}

	public GameEventNotifier getGameEventNotifier() {
		return (gameEventNotifier);
	}

	///////////////////////////////////////////////////////////////////////////

	public void setPlayerEncounteredAMonster(boolean b) {
		playerEncounteredAMonster = b;
	}

	public void setPlayer(Player player) {
		this._player = player;
		player.setTurn(this);
	}

	public void clearPlayer() {
		_player.setTurn(null);
		this._player = null;
	}

	public void setDidPlayerEncounterAMonster(boolean b) {
		this.playerEncounteredAMonster = b;
	}

	private void setGame(Game game) {
		this._game = game;
	}

	///////////////////////////////////////////////////////////////////////////

	public String toString() {
		return ("Turn");
	}

	///////////////////////////////////////////////////////////////////////////

	public Iterator<IPhase> iterator() {
		nextPhase = PhaseMarker.begin;
		return new CHackIterator(this);
	}

	public boolean hasNext() {
		return (nextPhase != PhaseMarker.done ? true : false);
	}

	public IPhase getNext() {
		return trasitionPhase();
	}

	private IPhase trasitionPhase() {

		switch (nextPhase) {
		case begin:
			nextPhase = PhaseMarker.door;
			return beginTurn;
		case door:
			nextPhase = PhaseMarker.trouble;
			return openADoor;
		case trouble:
			nextPhase = PhaseMarker.loot;
			if (!playerEncounteredAMonster)
				return lookForTrouble;
		case loot:
			nextPhase = PhaseMarker.discard;
			if (!playerEncounteredAMonster)
				return lootTheRoom;
		case discard:
			nextPhase = PhaseMarker.end;
			if (_player.numberOfCardsInHand() > _player.getMaxHandSize())
				return charity;
		case end:
			nextPhase = PhaseMarker.done;
			return endTurn;
		default:
			return null;
		}

	}
}
