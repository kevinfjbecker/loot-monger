package deck;


import java.util.ArrayList;

import listener.GameObserver;

import card.DiscardCarriedItemsBadStuff;
import card.DiscardFromHandBadStuff;
import card.IDoesBadStuff;
import card.LevelLossBadStuff;
import card.PlayerDeathBadStuff;
import card.CCard;
import card.CCurseCard;
import card.CDungeonCard;
import card.CItemCard;
import card.CMonsterCard;
import card.COneShotCard;
import card.CTreasureCard;

public class DeckBuilder {
	/**
	 * @uml.property   name="_dungeonDeck"
	 * @uml.associationEnd   multiplicity="(0 -1)" elementType="card.CDungeonCard"
	 */
	private CDeck<CDungeonCard> _dungeonDeck;

	/**
	 * @uml.property   name="_dungeonCardList"
	 * @uml.associationEnd   multiplicity="(0 -1)" elementType="card.CDungeonCard"
	 */
	private ArrayList<CDungeonCard> _dungeonCardList;

	/**
	 * @uml.property   name="_dungeonCardPile"
	 * @uml.associationEnd   multiplicity="(0 -1)" elementType="card.CDungeonCard"
	 */
	private ArrayList<CDungeonCard> _dungeonCardPile;

	/**
	 * @uml.property   name="_treasureDeck"
	 * @uml.associationEnd   multiplicity="(0 -1)" elementType="card.CTreasureCard"
	 */
	private CDeck<CTreasureCard> _treasureDeck;

	/**
	 * @uml.property   name="_treasureCardList"
	 * @uml.associationEnd   multiplicity="(0 -1)" elementType="card.CTreasureCard"
	 */
	private ArrayList<CTreasureCard> _treasureCardList;

	/**
	 * @uml.property   name="_treasureCardPile"
	 * @uml.associationEnd   multiplicity="(0 -1)" elementType="card.CTreasureCard"
	 */
	private ArrayList<CTreasureCard> _treasureCardPile;

	private GameObserver _gameObserver;
	
	public DeckBuilder(GameObserver gameObserver) {
		setGameObserver(gameObserver);
	}

	public CDeck<CDungeonCard> getDungeonDeck() {
		return(_dungeonDeck);
	}
	
	public CDeck<CTreasureCard> getTreasureDeck(){
		return(_treasureDeck);
	}
	
	public void setupDecksAndPiles() {
		instantiateCardLists();
		instantiatePiles();
		populateCardLists();
		buildDecks();
		ShuffleDecks();
	}

	private void instantiatePiles() {
		_dungeonCardPile = new ArrayList<CDungeonCard>();
		_treasureCardPile = new ArrayList<CTreasureCard>();
	}

	private void ShuffleDecks() {
		_dungeonDeck.shuffleCards();
		_treasureDeck.shuffleCards();
	}

	private void buildDecks() {
		_dungeonDeck = new CDeck<CDungeonCard>();
		linkGameObserverToCardsInList(_dungeonCardList);
		_dungeonDeck.setCardList(_dungeonCardList);
		_dungeonDeck.setDiscardPile(_dungeonCardPile);
		_treasureDeck = new CDeck<CTreasureCard>();
		linkGameObserverToCardsInList(_treasureCardList);
		_treasureDeck.setCardList(_treasureCardList);
		_treasureDeck.setDiscardPile(_treasureCardPile);
	}

	private void linkGameObserverToCardsInList(ArrayList<? extends CCard> cardList) {
		for( int i = 0 ; i < cardList.size() ; i++)
			cardList.get(i).getGameEventNotifier().addListener(_gameObserver);
	}

	private void instantiateCardLists() {
		_dungeonCardList = new ArrayList<CDungeonCard>();
		_dungeonCardPile = new ArrayList<CDungeonCard>();
		_treasureCardList = new ArrayList<CTreasureCard>();
		_treasureCardPile = new ArrayList<CTreasureCard>();
	}

	private void populateCardLists() {
		addOneShotCardsToTreasureCardList();
		addItemCardsToTreasureCardList();
		addMonsterCardsToDungeonCardList();
		addCurseCardsToDungeonCardList();
	}

	private void addItemCardsToTreasureCardList() {
		for (int i = 1; i <= 5; i++) {
			for (int k = 0; k < 10; k++) {
				CItemCard itemCard = new CItemCard();
				itemCard.setBonus(i);
				itemCard.setValue(i * 100 + 200);
				itemCard.setDiscardPile(_treasureCardPile);
				_treasureCardList.add(itemCard);
			}
		}
	}

	private void addOneShotCardsToTreasureCardList() {
		addX_YBonusOneShotCardsToTreasureCardList(5, 1);
		addX_YBonusOneShotCardsToTreasureCardList(10, 2);
		addX_YBonusOneShotCardsToTreasureCardList(10, 3);
		addX_YBonusOneShotCardsToTreasureCardList(5, 4);
	}

	private void addX_YBonusOneShotCardsToTreasureCardList(int x, int y) {
		for (int i = 0; i < x; i++) {
			COneShotCard oneShotCard = new COneShotCard();
			oneShotCard.setBonus(y);
			oneShotCard.setValue(y * 100);
			oneShotCard.setDiscardPile(_treasureCardPile);
			_treasureCardList.add(oneShotCard);
		}
	}

	private void addMonsterCardsToDungeonCardList() {
		addLoseLevelsBadStuffMonstersToDungeonCardList();
		addPlayerDeathBadStuffMonstersToDungeonCardList();
	}

	private void addPlayerDeathBadStuffMonstersToDungeonCardList() {
		for (int i = 10; i < 20; i++) {
			CMonsterCard monsterCard = new CMonsterCard();
			monsterCard.setLevel(i);
			PlayerDeathBadStuff playerDeathBadStuff = new PlayerDeathBadStuff();
			monsterCard.setDoesBadStuff(playerDeathBadStuff);
			monsterCard.setNumberOfTreasures((int) Math.ceil(Math.sqrt(i)));
			monsterCard.setDiscardPile(_dungeonCardPile);
			_dungeonCardList.add(monsterCard);
		}
	}

	private void addLoseLevelsBadStuffMonstersToDungeonCardList() {
		for (int i = 1; i <= 9; i++) {
			_dungeonCardList.add(getLoseLevelsMonster(i));
			_dungeonCardList.add(getLoseLevelsMonster(i));
			_dungeonCardList.add(getLoseCarriedItemMonster(i));
			_dungeonCardList.add(getLoseCarriedItemMonster(i));
			_dungeonCardList.add(getLoseCardsFromHandMonster(i));
		}
		_dungeonCardList.add(getLoseLevelsMonster(1));
		_dungeonCardList.add(getLoseLevelsMonster(2));
		_dungeonCardList.add(getLoseCarriedItemMonster(3));
		_dungeonCardList.add(getLoseCarriedItemMonster(4));
		_dungeonCardList.add(getLoseCardsFromHandMonster(5));
	}

	
	private CDungeonCard getLoseCardsFromHandMonster(int i) {
		CMonsterCard monsterCard = getMonsterWithoutBadStuff(i);
		monsterCard.setDoesBadStuff(getDiscardFromHandBadStuff(i));
		return(monsterCard);
	}

	private CDungeonCard getLoseCarriedItemMonster(int i) {
		CMonsterCard monsterCard = getMonsterWithoutBadStuff(i);
		monsterCard.setDoesBadStuff(getDiscardCarriedItemsBadStuff(i));
		return(monsterCard);
	}

	private CMonsterCard getLoseLevelsMonster(int i) {
		CMonsterCard monsterCard = getMonsterWithoutBadStuff(i);
		monsterCard.setDoesBadStuff(getLevelLossBadStuff(i));
		return(monsterCard);
	}
	
	private IDoesBadStuff getDiscardCarriedItemsBadStuff(int i) {
		return(new DiscardCarriedItemsBadStuff((int) Math.ceil(Math.sqrt(i))));
	}

	private IDoesBadStuff getDiscardFromHandBadStuff(int i) {
		return(new DiscardFromHandBadStuff((int) Math.ceil(Math.sqrt(i))));
	}
	
	private LevelLossBadStuff getLevelLossBadStuff(int i){
		return(new LevelLossBadStuff((int) Math.ceil(Math.sqrt(i))));
	}
	
	private CMonsterCard getMonsterWithoutBadStuff(int i) {
		CMonsterCard monsterCard = new CMonsterCard();
		monsterCard.setLevel(i);
		monsterCard.setNumberOfTreasures((int) Math.ceil(Math.sqrt(i)));
		monsterCard.setDiscardPile(_dungeonCardPile);
		return monsterCard;
	}

	private void addCurseCardsToDungeonCardList() {
		addXLoseYLevelsCurseCardsToDungeonCardList(10, 1);
		addXLoseYLevelsCurseCardsToDungeonCardList(5, 2);
		addXLoseYLevelsCurseCardsToDungeonCardList(3, 3);
		addXLoseYLevelsCurseCardsToDungeonCardList(2, 4);
	}

	private void addXLoseYLevelsCurseCardsToDungeonCardList(int x, int y) {
		for (int i = 0; i < x; i++) {
			CCurseCard curseCard = new CCurseCard();
			curseCard.setLevelLoss(y);
			curseCard.setDiscardPile(_dungeonCardPile);
			_dungeonCardList.add(curseCard);
		}
	}

	private void setGameObserver(GameObserver gameObserver) {
		this._gameObserver = gameObserver;
	}
}