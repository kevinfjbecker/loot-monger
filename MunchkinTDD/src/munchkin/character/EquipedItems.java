package munchkin.character;

import munchkin.card.playarea.PlayArea;
import munchkin.card.terasure.item.Headgear;
import munchkin.card.terasure.item.ItemCard;

public class EquipedItems extends PlayArea<ItemCard> {
	
	public EquipedItems(){
		super();
	}

	public void equipItem(ItemCard itemCard) {
		addACard(itemCard);
	}

	public int getTotalCombatBonus() {
		int totalCombatBonus = 0;
		for (int i = 0; i < cardList.size(); i++) {
			totalCombatBonus += cardList.get(i).getBonus();
		}
		return (totalCombatBonus);
	}

	public void discardHeadgear() {
		for (int i = 0; i < cardList.size(); i++) {
			if(cardList.get(i) instanceof Headgear ) {
				cardList.remove(i).discard();
			}
		}
	}
}
