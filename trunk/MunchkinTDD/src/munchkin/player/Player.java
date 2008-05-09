package munchkin.player;

import munchkin.card.Card;
import munchkin.card.dungeon.monster.MonsterCard;
import munchkin.card.terasure.item.ItemCard;
import munchkin.character.Character;
import munchkin.character.charcterclass.CharacterClass;
import munchkin.character.race.Race;
import munchkin.character.sex.Sex;
import munchkin.deck.Deck;

public class Player {

	private Character character;

	private Hand hand;

	public Player() {
		character = new Character();
		hand = new Hand();
	}

	public void gainLevels(int i) {
		character.gainLevels(i);
	}

	public int getLevel() {
		return (character.getLevel());
	}

	public void setRace(Race race) {
		character.setRace(race);
	}

	public boolean isRace(Race race) {
		return (character.isRace(race));
	}

	public boolean isDead() {
		return (character.isDead());
	}

	public void die() {
		character.die();
	}

	public void loseLevels(int i) {
		character.loseLevels(i);
	}

	public void setSex(Sex sex) {
		character.setSex(sex);
	}

	public boolean isSex(Sex sex) {
		return (character.isSex(sex));
	}

	public int getNumberOfCardsInHand() {
		return (hand.getNumberOfCards());
	}

	public void drawACard(Deck<? extends Card> deck) {
		if (!deck.hasRunOutAndThereAreNoDiscards())
			hand.putCardInHand(deck.drawACard());
	}

	public boolean isCharacterClass(CharacterClass characterClass) {
		return (character.isCharacterClass(characterClass));
	}

	public void loseCharacterClass() {
		character.loseCharacterClass();
	}

	public void setCharacterClass(CharacterClass characterClass) {
		character.setCharacterClass(characterClass);

	}

	public void carryItem(ItemCard itemCard) {
		if(hand.contains(itemCard)) {
			character.carryItem((ItemCard)hand.remove(itemCard));
		}
	}

	public int getNumberCarriedItems() {
		return(character.getNumberCarriedItems());
	}

	public int getNumberEquipedItems() {
		return character.getNumberEquipedItems();
	}

	public void equipItem(ItemCard itemCard) {
		if (hand.contains(itemCard))
			character.equipItem((ItemCard)hand.remove(itemCard));
		else
			character.equipCarriedItem(itemCard);
	}

	public void unequipItem(ItemCard itemCard) {
		character.unequipItem(itemCard);
	}

	public int getEffectiveLevel() {
		return(character.getEffectiveLevel());
	}

	public int getEquipmentBonus() {
		return character.getTotalCombatBonus();
	}

	public void loseEquipedHeadgear() {
		character.loseEquipedHeadgear();
	}
	
	public boolean runAway(MonsterCard monsterCard) {
		return (character.runAway(monsterCard));
	}

	// TODO TESTING CODE; REMOVE LATER
	public Character getCharacter() {
		return character;
	}

	public void discardCard(Card card) {
		hand.remove(card).discard();
	}
	
	public void loseItem(ItemCard itemCard){
		character.loseItem(itemCard);
	}
}
