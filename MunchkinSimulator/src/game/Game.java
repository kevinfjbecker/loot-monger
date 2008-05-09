package game;

import handler.Executor;
import handler.OutputHandler;
import listener.GameEventNotifier;
import listener.GameObserver;
import listener.IGameEventListener;
import message.event.DrawTwoCardsFromEachDeckEvent;
import message.event.IGameEvent;
import message.event.PlayerWinsGameEvent;
import player.Player;
import player.PlayerManager;
import turn.CTurn;
import turn.IPhase;
import deck.DeckManager;

public class Game implements IGameEventListener {

	private DeckManager _deckManager;

	private PlayerManager _playerManager;

	private GameObserver _gameObserver;

	private GameEventNotifier gameEventNotifier;

	private boolean endOfGame;

	private CTurn _turnStructure;

	public Game() {

		endOfGame = false;

		_gameObserver = new GameObserver();

		_gameObserver.setHandlerChain(new OutputHandler(new Executor(null)));

		gameEventNotifier = new GameEventNotifier();

		setDeckManager(new DeckManager(getGameObserver()));

		setPlayerManager(new PlayerManager(getGameObserver()));

		_turnStructure = new CTurn(this);

		becomeObserverOfGameObserver();

	}

	///////////////////////////////////////////////////////////////////////////
	

	public void becomeObserverOfGameObserver() {

		// this creates a feed-back loop to watch for the end of the game
		_gameObserver.setGameObserver(this);

	}

	public void observe(IGameEvent gameEvent) {
		if (gameEvent instanceof PlayerWinsGameEvent)
			endOfGame = true;
	}
	
	public GameObserver getGameObserver() {
		return (_gameObserver);
	}

	///////////////////////////////////////////////////////////////////////////

	public void play() {
		characterCreationPhase();
		while (!endOfGame)
			for (Player player : getPlayerManager())
				for (IPhase phase : getTurnStructure()) {
					if (endOfGame)
						break;
					phase.play(player);
				}
	}

	private CTurn getTurnStructure() {
		return _turnStructure;
	}

	///////////////////////////////////////////////////////////////////////////

	private void setDeckManager(DeckManager deckManager) {
		_deckManager = deckManager;
	}

	public DeckManager getDeckManager() {
		return (_deckManager);
	}

	///////////////////////////////////////////////////////////////////////////

	public void addPlayer(String playerName, String agentName) {
		getPlayerManager().addPlayer(playerName, agentName);
	}

	private void setPlayerManager(PlayerManager playerManager) {
		_playerManager = playerManager;
	}

	public PlayerManager getPlayerManager() {
		return (_playerManager);
	}

	///////////////////////////////////////////////////////////////////////////

	public void characterCreationPhase() {
		for (Player player : getPlayerManager()) {
			drawTwoCardsFromEachDeck(player);
			player.inform_equipItems();
		}
	}

	public void drawTwoCardsFromEachDeck(Player player) {
		gameEventNotifier.notifyListeners(new DrawTwoCardsFromEachDeckEvent(
				player));
		player.drawACard(getDeckManager().getDungeonDeck());
		player.drawACard(getDeckManager().getDungeonDeck());
		player.drawACard(getDeckManager().getTreasureDeck());
		player.drawACard(getDeckManager().getTreasureDeck());
	}

	///////////////////////////////////////////////////////////////////////////

	/*
	 *  test code: to ensure that cards are
	 *  not getting lost or duplicated
	 */
	public int getNumberOfCardsInGame() {
		int cardsInGame = 0;
		cardsInGame += getPlayerManager().getNumberOfCardsHeldByPlayers();
		cardsInGame += getDeckManager().getNumberOfCardsInDecksAndPiles();
		return (cardsInGame);
	}

}
