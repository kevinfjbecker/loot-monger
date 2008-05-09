package player;

import deck.CDeck;
import dice.ASixSidedDie;

import java.util.ArrayList;

import listener.GameEventNotifier;
import message.command.AgentMayChooseToInterfereWithCombat;
import message.command.AgentMayChooseToPlayOneShot;
import message.command.AgentMayLookForTrouble;
import message.command.AgentMustDecideOnALootCard;
import message.command.AgentMustDiscardCarriedItems;
import message.command.AgentMustDiscardFromHandEvent;
import message.event.EquipItemEvent;
import message.event.LevelGainEvent;
import message.event.LevelLossEvent;
import message.event.PlayerDeathEvent;
import message.event.PlayerWinsGameEvent;

import turn.CLookForTroublePhase;
import turn.CCombat;
import turn.CTurn;
import card.CCard;
import card.CItemCard;
import card.CMonsterCard;
import card.COneShotCard;

public class Player {

	private Agent agent;

	private ArrayList<CCard> hand;

	private ArrayList<CItemCard> carriedItems;

	private int level;

	private String name;

	private boolean diedLastTurn = false;

	private CTurn turn;

	private GameEventNotifier gameEventNotifier;

	private ArrayList<Integer> _rollOffRolls = new ArrayList<Integer>();

	public String getName() {
		return (name);
	}

	public int getLevel() {
		return (level);
	}

	public GameEventNotifier getGameEventNotifier() {
		return (gameEventNotifier);
	}

	public String getAgentName() {
		return (agent.getName());
	}

	public ArrayList<CItemCard> getCarriedItems() {
		return (carriedItems);
	}

	public int getMaxHandSize() {
		return (5);
	}

	public CTurn getTurn() {
		return (turn);
	}

	public int getEffectiveLevel() {
		return (level + getCarriedItemsBonus());
	}

	public int getCarriedItemsBonus() {
		int sumOfCarriedItemBonuses = 0;
		for (int i = 0; i < carriedItems.size(); i++)
			sumOfCarriedItemBonuses += carriedItems.get(i).getBonus();
		return (sumOfCarriedItemBonuses);
	}

	public ArrayList<CCard> getHand() {
		return (hand);
	}

	public void setHand(ArrayList<CCard> hand) {
		this.hand = hand;
	}

	public void setCariedItems(ArrayList<CItemCard> carriedItems) {
		this.carriedItems = carriedItems;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGameEventNotifier(GameEventNotifier gameEventNotifier) {
		this.gameEventNotifier = gameEventNotifier;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public void setTurn(CTurn turn) {
		this.turn = turn;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean didDieLastTurn() {
		return (diedLastTurn);
	}

	public void die() {
		gameEventNotifier.notifyListeners(new PlayerDeathEvent(this));
		diedLastTurn = true;
	}

	public void resetDiedLastTurnToggle() {
		diedLastTurn = false;
	}

	public boolean runAway(CMonsterCard card) {
		if (ASixSidedDie.roll() >= 5)
			return (true);
		return (false);
	}

	public int numberOfCardsOverMaxHandSize() {
		return (numberOfCardsInHand() - getMaxHandSize());
	}

	public void drawACard(CDeck<? extends CCard> deck) {
		if (!deck.hasRunOutAndThereAreNoDiscards())
			hand.add(deck.drawACard());
	}

	public void addCardToHand(CCard simpleCard) {
		hand.add(simpleCard);
	}

	public int numberOfCardsInHand() {
		return (hand.size());
	}

	public void equipItem(CItemCard newEquipment) {
		gameEventNotifier
				.notifyListeners(new EquipItemEvent(this, newEquipment));
		carriedItems.add(newEquipment);
	}

	public void loseLevels(int levelLoss) {
		int netLevelLoss = calculateLevelLossDownToAMinimunLevelOfOne(levelLoss);
		gameEventNotifier.notifyListeners(new LevelLossEvent(this, levelLoss,
				netLevelLoss));
		level -= netLevelLoss;
	}

	private int calculateLevelLossDownToAMinimunLevelOfOne(int levelLoss) {
		if (level - levelLoss < 1)
			return (levelLoss - (levelLoss - (level - 1)));
		return (levelLoss);
	}

	public void gainLevels(int levelGain) {
		level += levelGain;
		gameEventNotifier.notifyListeners(new LevelGainEvent(this, levelGain));
		if (checkIfPlayerHasWonGame(this)) {
			gameEventNotifier.notifyListeners(new PlayerWinsGameEvent(this));
		}
	}

	private boolean checkIfPlayerHasWonGame(Player player) {
		if (player.getLevel() >= 10)
			return (true);
		return (false);
	}

	public String toString() {
		String s = getName() + "{ ";
		s += "die rolls:" + _rollOffRolls.toString();
		s += "\n";
		s += "\tlevel: " + level + "\n";
		s += "\thand[\n";
		if (!hand.isEmpty())
			s += "\t\t" + hand.get(0) + "\n";
		for (int i = 1; i < hand.size(); i++)
			s += "\t\t" + hand.get(i) + "\n";
		s += "\t]\n\tcarriedItems[\n";
		if (!carriedItems.isEmpty())
			s += "\t\t" + carriedItems.get(0) + "\n";
		for (int i = 1; i < carriedItems.size(); i++)
			s += "\t\t" + carriedItems.get(i) + "\n";
		s += "\t]\n}";
		return (s);
	}

	// ////////////////////////////////////////////////////////////////////////
	
	public void inform_discardCardsFromHand(int numberOfCardsToDiscard) {
		gameEventNotifier
				.notifyListeners(new AgentMustDiscardFromHandEvent(agent, Math
						.min(numberOfCardsToDiscard, numberOfCardsInHand())));
	}

	public void inform_lookForTrouble(CLookForTroublePhase troublePhase) {
		gameEventNotifier.notifyListeners(new AgentMayLookForTrouble(agent,
				getMonsterCardsFromHand(), troublePhase));
	}

	public boolean isAbleToLookForTrouble() {
		return (!getMonsterCardsFromHand().isEmpty());
	}

	public void inform_equipItems() {
		agent.equipItems();
	}

	public void inform_playOneShotCards(CCombat combat) {
		ArrayList<COneShotCard> oneShotCards = getOneShotCardsFromHand();
		if (!oneShotCards.isEmpty())
			gameEventNotifier.notifyListeners(new AgentMayChooseToPlayOneShot(
					agent, oneShotCards, combat));
	}

	public void inform_collectLootCard(ArrayList<CCard> lootCardList) {
		gameEventNotifier.notifyListeners(new AgentMustDecideOnALootCard(agent,
				lootCardList));
	}

	public void inform_interfereWithCombat(CCombat combat) {
		ArrayList<COneShotCard> oneShotCards = getOneShotCardsFromHand();
		if (!oneShotCards.isEmpty())
			gameEventNotifier
					.notifyListeners(new AgentMayChooseToInterfereWithCombat(
							agent, oneShotCards, combat));
	}

	public void inform_discardCarriedItems(int numberOfDiscards) {
		gameEventNotifier
				.notifyListeners(new AgentMustDiscardCarriedItems(agent,
						Math.min(numberOfDiscards, getCarriedItems().size())));
	}

	// ////////////////////////////////////////////////////////////////////////
	
	private class ElementFilter<T extends CCard> {
		@SuppressWarnings("unchecked")
		public ArrayList<T> getCardsOfTypeTFromList(
				ArrayList<? extends CCard> cardList, Class aClass) {
			ArrayList<T> cardsOfTypeT = new ArrayList<T>();
			for (CCard card : cardList)
				if (card.getClass().equals(aClass))
					cardsOfTypeT.add((T) card);
			return (cardsOfTypeT);
		}
	}
	
	private ArrayList<COneShotCard> getOneShotCardsFromHand() {
		return (new ElementFilter<COneShotCard>().getCardsOfTypeTFromList(
				getHand(), COneShotCard.class));
	}
	
	private ArrayList<CMonsterCard> getMonsterCardsFromHand() {
		return (new ElementFilter<CMonsterCard>().getCardsOfTypeTFromList(
				getHand(), CMonsterCard.class));
	}
	
	// ////////////////////////////////////////////////////////////////////////

	public int getRollOff(int i) {
		if (_rollOffRolls.size() <= i) {
			for (int k = _rollOffRolls.size(); k <= i; k++) {
				_rollOffRolls.add(ASixSidedDie.roll());
			}
		}
		return (_rollOffRolls.get(i));
	}

	public void clearRollOffRolls() {
		_rollOffRolls.clear();
	}
}
