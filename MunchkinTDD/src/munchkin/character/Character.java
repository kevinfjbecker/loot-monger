package munchkin.character;

import munchkin.card.dungeon.monster.MonsterCard;
import munchkin.card.dungeon.monster.Types;
import munchkin.card.strategy.IRunAway;
import munchkin.card.strategy.implementation.BasicRunAwayStrategy;
import munchkin.card.strategy.implementation.CStrategyContext;
import munchkin.card.terasure.item.ItemCard;
import munchkin.character.charcterclass.CharacterClass;
import munchkin.character.charcterclass.NoClass;
import munchkin.character.race.Human;
import munchkin.character.race.Race;
import munchkin.character.sex.Sex;

public class Character {

	private boolean isAlive;

	private int level;

	private Race race;

	private Sex sex;

	private CharacterClass characterClass;

	private CarriedItems carriedItems;

	private EquipedItems equipedItems;
	
	private CStrategyContext strategies;
	
	public CStrategyContext getStrategyContext() {
		return (strategies);
	}

	public Character() {
		isAlive = true;
		level = 1;
		race = new Human();
		characterClass = new NoClass();
		carriedItems = new CarriedItems();
		equipedItems = new EquipedItems();
		strategies = new CStrategyContext();
		getStrategyContext().addStrategy(new BasicRunAwayStrategy());
	}

	public void gainLevels(int i) {
		level += i;
	}

	public int getLevel() {
		return (level);
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public boolean isRace(Race race) {
		return (this.race.equals(race));
	}

	public boolean isDead() {
		return (!isAlive);
	}

	public void die() {
		isAlive = false;
	}

	public void loseLevels(int i) {
		level -= i;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public boolean isSex(Sex sex) {
		return (this.sex.equals(sex));
	}

	public boolean isCharacterClass(CharacterClass characterClass) {
		return (this.characterClass.equals(characterClass));
	}

	public void loseCharacterClass() {
		characterClass = new NoClass();
	}

	public void setCharacterClass(CharacterClass characterClass) {
		this.characterClass = characterClass;
	}

	public void carryItem(ItemCard itemCard) {
		carriedItems.carryItem(itemCard);
	}

	public int getNumberCarriedItems() {
		return (carriedItems.getNumberOfCards());
	}

	public int getNumberEquipedItems() {
		return (equipedItems.getNumberOfCards());
	}

	public void equipItem(ItemCard itemCard) {
		itemCard.equip(this);
		equipedItems.equipItem(itemCard);
	}

	public void equipCarriedItem(ItemCard itemCard) {
		equipItem(carriedItems.remove(itemCard));
	}

	public void unequipItem(ItemCard itemCard) {
		itemCard.unequip();
		carriedItems.carryItem(equipedItems.remove(itemCard));
	}

	public int getEffectiveLevel() {
		return (getTotalCombatBonus() + getLevel());
	}

	public int getTotalCombatBonus() {
		return (equipedItems.getTotalCombatBonus());
	}

	public void loseEquipedHeadgear() {
		equipedItems.discardHeadgear();
	}

	public boolean runAway(MonsterCard monsterCard) {
		return(((IRunAway)getStrategyContext().getStrategy(Types.runAway)).runAway(monsterCard));
	}

	public void loseItem(ItemCard itemCard) {
		if(carriedItems.contains(itemCard))
			carriedItems.remove(itemCard).discard();
		else
			equipedItems.remove(itemCard).discard();
	}

}
