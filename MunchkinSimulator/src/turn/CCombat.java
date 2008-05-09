package turn;

import java.util.ArrayList;

import player.Player;
import card.CCard;
import card.CMonsterCard;
import card.COneShotCard;

public class CCombat {

	private CMonsterCard monster;

	private Player player;

	private ArrayList<COneShotCard> monsterSideOneShotList;

	private ArrayList<COneShotCard> adventurerSideOneShotList;

	private boolean newCardsWerePlayedToggle;

	public CCombat(Player player, CMonsterCard monster) {
		newCardsWerePlayedToggle = false;
		monsterSideOneShotList = new ArrayList<COneShotCard>();
		adventurerSideOneShotList = new ArrayList<COneShotCard>();
		setPlayer(player);
		setMonster(monster);
	}

	private void setMonster(CMonsterCard monster) {
		this.monster = monster;
	}

	private void setPlayer(Player player) {
		this.player = player;
	}

	public void AddOneShotToMonsterSide(COneShotCard oneShotCard) {
		this.monsterSideOneShotList.add(oneShotCard);
		newCardsWerePlayedToggle = true;
	}

	public void AddOneShotToAventurerSide(COneShotCard oneShotCard) {
		this.adventurerSideOneShotList.add(oneShotCard);
		newCardsWerePlayedToggle = true;
	}

	public int getAdventurerSideEffectiveLevel() {
		return (player.getEffectiveLevel() + this
				.getAdventurerSideOneShotBonusTotal());
	}

	public int getAdventurerSideOneShotBonusTotal() {
		return (getOneShotListBonusTotal(adventurerSideOneShotList));
	}

	private int getOneShotListBonusTotal(
			ArrayList<COneShotCard> oneShotList) {
		int oneShotBonusTotal = 0;
		for (int i = 0; i < oneShotList.size(); i++) {
			oneShotBonusTotal += oneShotList.get(i).getBonus();
		}
		return (oneShotBonusTotal);
	}

	public int getMonsterSideEffectiveLevel() {
		return (monster.getEffectiveLevel() + this
				.getMosnterSideOneShotBonusTotal());
	}

	public int getMosnterSideOneShotBonusTotal() {
		return (getOneShotListBonusTotal(monsterSideOneShotList));
	}

	public void resetNewCardsWerePlayedToggle() {
		newCardsWerePlayedToggle = false;
	}

	public boolean newCardsWerePlayered() {
		return (newCardsWerePlayedToggle);
	}

	public boolean adventurerSideDefeatsMonsters() {
		return (this.getAdventurerSideEffectiveLevel() > this
				.getMonsterSideEffectiveLevel());
	}

	public void discardCards() {
		discardAndClearCardList(adventurerSideOneShotList);
		discardAndClearCardList(monsterSideOneShotList);
		monster.discard();
	}

	private void discardAndClearCardList(
			ArrayList<? extends CCard> cardList) {
		for (int i = 0; i < cardList.size(); i++) {
			cardList.get(i).discard();
		}
		cardList.clear();
	}

	public Player getPlayer() {
		return (player);
	}

	public CMonsterCard getMonster() {
		return (monster);
	}

	public String toString() {
		String s = "";
		s += "Combat[ (";

		if (adventurerSideDefeatsMonsters())
			s += "Adventurer's Side: " + getAdventurerSideEffectiveLevel()
					+ " to " + getMonsterSideEffectiveLevel();
		else
			s += "Monster's Side: " + getMonsterSideEffectiveLevel() + " to "
					+ getAdventurerSideEffectiveLevel();

		s += ")\n";
		s += "\tPlayer{\n";
		s += "\t\t" + player.getName() + "\n";
		s += "\t\tlevel: " + player.getLevel() + "\n";
		s += "\t\tequipment bonus: ";
		s += player.getCarriedItemsBonus() + "\n";
		s += "\t\tone-shot bonus:" + getAdventurerSideOneShotBonusTotal()
				+ "\n";
		s += "\t}\n";
		s += "\tMonster{\n";
		s += "\t\tlevel: " + monster.getEffectiveLevel() + "\n";
		s += "\t\tone-shot bonus: " + this.getMosnterSideOneShotBonusTotal()
				+ "\n";
		s += "\t}\n";
		s += "]";
		return (s);
	}
}
