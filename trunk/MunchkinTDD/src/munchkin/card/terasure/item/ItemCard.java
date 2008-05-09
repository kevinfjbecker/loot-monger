package munchkin.card.terasure.item;

import munchkin.card.TreasureCard;
import munchkin.character.Character;


public abstract class ItemCard extends TreasureCard {

	/*
	 *  constructor
	 */
	public ItemCard(){
		super();
	}

	protected Character character;

	public abstract int getBonus();

	public abstract int getValueInGoldPieces();
	
	public void equip(Character character){
		this.character = character;
	}
	public void unequip(){
		this.character = null;
	}
	public void discard(){
		super.discard();
		unequip();
	}
}
