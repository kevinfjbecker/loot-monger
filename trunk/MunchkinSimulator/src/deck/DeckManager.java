
package deck;

import listener.GameObserver;
import card.CDungeonCard;
import card.CTreasureCard;

public class DeckManager {

	private GameObserver _gameObserver;
	private DeckBuilder _deckBuilder;
	private CDeck<CDungeonCard> _dungeonDeck;
	private CDeck<CTreasureCard> _treasureDeck;
	
	public DeckManager(GameObserver gameObserver){
		setGameObserver(gameObserver);
		setDeckBuilder(new DeckBuilder(getGameObserver()));
		getDeckBuilder().setupDecksAndPiles();
		setDungeonDeck(getDeckBuilder().getDungeonDeck());
		setTreasureDeck(getDeckBuilder().getTreasureDeck());
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private void setDungeonDeck(CDeck<CDungeonCard> dungeonDeck) {
		_dungeonDeck = dungeonDeck;
	}

	private void setTreasureDeck(CDeck<CTreasureCard> treasureDeck) {
		_treasureDeck = treasureDeck;
	}
	
	private void setDeckBuilder(DeckBuilder deckBuilder) {
		_deckBuilder = deckBuilder;
	}
	
	private void setGameObserver(GameObserver gameObserver) {
		_gameObserver = gameObserver;
	}

	///////////////////////////////////////////////////////////////////////////
	
	public CDeck<CTreasureCard> getTreasureDeck() {
		return (_treasureDeck);
	}

	public CDeck<CDungeonCard> getDungeonDeck() {
		return (_dungeonDeck);
	}

	private DeckBuilder getDeckBuilder() {
		return _deckBuilder;
	}

	private GameObserver getGameObserver() {
		return _gameObserver;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public int getNumberOfCardsInDecksAndPiles() {
		int cardsInGame = 0;
		cardsInGame += getDungeonDeck().size();
		cardsInGame += getDungeonDeck().getDiscardPile().size();
		cardsInGame += getTreasureDeck().size();
		cardsInGame += getTreasureDeck().getDiscardPile().size();
		return (cardsInGame);
	}
	
}
