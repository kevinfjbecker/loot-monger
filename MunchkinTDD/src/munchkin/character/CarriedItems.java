package munchkin.character;

import munchkin.card.playarea.PlayArea;
import munchkin.card.terasure.item.ItemCard;

public class CarriedItems extends PlayArea<ItemCard>{
	
	public CarriedItems() {
		super();
	}

	public void carryItem(ItemCard itemCard) {
		addACard(itemCard);
	}


}
