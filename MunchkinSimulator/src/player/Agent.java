package player;

import java.util.ArrayList;

import listener.GameEventNotifier;
import message.command.PlayerDoesNotLookForTrouble;
import message.command.PlayerLooksForTrouble;
import message.command.PlayerPlaysOneShotCardForAdventurerSide;
import message.command.PlayerPlaysOneShotCardForMonsterSide;
import message.command.PlayerDiscardsCarriedItem;
import message.command.PlayerDiscardsFromHand;
import message.command.PlayerTakesLootCard;

import turn.CLookForTroublePhase;
import turn.CCombat;
import card.CCard;
import card.CItemCard;
import card.CMonsterCard;
import card.COneShotCard;

public class Agent {

	private String name;

	private Player player;

	private GameEventNotifier gameEventNotifier;

	public Agent(String name) {
		this.name = name;
	}

	public String getName() {
		return (name);
	}

	public void setGameEventNotifier(GameEventNotifier gameEventNotifier) {
		this.gameEventNotifier = gameEventNotifier;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public GameEventNotifier getGameEventNotifier() {
		return (gameEventNotifier);
	}
	
	public void discardCardsFromHand(int numberOfCardsToDiscard) {
		for (int i = 0; i < numberOfCardsToDiscard; i++) {
			CCard discard = chooseCardAtRandom(player.getHand());
			gameEventNotifier.notifyListeners(new PlayerDiscardsFromHand(
					player, discard));
		}
	}
	
	public void discardCarriedItems(int numberOfCardsToDiscard) {
		for (int i = 0; i < numberOfCardsToDiscard; i++) {
			CCard discard = chooseCardAtRandom(player.getCarriedItems());
			gameEventNotifier
					.notifyListeners(new PlayerDiscardsCarriedItem(player,
							discard));
		}
	}

	public void equipItems() {
		// TODO: this choice should be an event
		for (int i = player.getHand().size() - 1; i >= 0; i--) {
			if (player.getHand().get(i) instanceof CItemCard
					&& !(player.getHand().get(i) instanceof COneShotCard)) {
				player.equipItem((CItemCard) player.getHand().remove(i));
			}
			if (player.getHand().isEmpty())
				return;
		}
	}

	public void playOneShotCards(ArrayList<COneShotCard> oneShotCards,
			CCombat combat) {
		if (!combat.adventurerSideDefeatsMonsters()) {
			COneShotCard oneShotCard = (COneShotCard) chooseCardAtRandom(oneShotCards);
			gameEventNotifier
					.notifyListeners(new PlayerPlaysOneShotCardForAdventurerSide(
							player, oneShotCard, combat));
		}
	}
	
	public void interfereWithCombat(ArrayList<COneShotCard> oneShotCards,
			CCombat combat) {
		if (Math.random() > .5) { // do interfere, otherwise do nothing
			if (Math.random() > .5) { // be good
				if (!combat.adventurerSideDefeatsMonsters()) {
					COneShotCard oneShotCard = (COneShotCard) chooseCardAtRandom(oneShotCards);
					gameEventNotifier
							.notifyListeners(new PlayerPlaysOneShotCardForAdventurerSide(
									player, oneShotCard, combat));
				}
			} else { // be evil
				if (combat.adventurerSideDefeatsMonsters()) {
					COneShotCard oneShotCard = (COneShotCard) chooseCardAtRandom(oneShotCards);
					gameEventNotifier
							.notifyListeners(new PlayerPlaysOneShotCardForMonsterSide(
									player, oneShotCard, combat));
				}
			}
		}
	}

	public void lookForTrouble(ArrayList<CMonsterCard> monsterCardsFromHand, CLookForTroublePhase troublePhase) {
		if (Math.random() > .25) { // do look for trouble, otherwise do inform phase
			CMonsterCard monster = (CMonsterCard)chooseCardAtRandom(monsterCardsFromHand);
			gameEventNotifier.notifyListeners(new PlayerLooksForTrouble(player,monster,troublePhase));
		} else {
			gameEventNotifier.notifyListeners(new PlayerDoesNotLookForTrouble(player,troublePhase));
		}
	}

	public void selectLootCard(ArrayList<CCard> lootCardList) {
		
		CCard lootedCard = chooseCardAtRandom(lootCardList);
		
		gameEventNotifier.notifyListeners(new PlayerTakesLootCard(player,
				lootedCard, lootCardList));
	}
	
	private CCard chooseCardAtRandom(ArrayList<? extends CCard> cardList) {
		return (cardList
				.get((int) (Math.floor(Math.random() * cardList.size()))));
	}

}
