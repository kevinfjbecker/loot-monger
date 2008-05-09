package message.command;

import java.util.ArrayList;


import player.Player;
import card.CCard;

public class PlayerTakesLootCard implements IGameCommand {

	private final Player player;

	private CCard lootedCard;

	private ArrayList<CCard> lootCardList;

	public PlayerTakesLootCard(Player player, CCard lootedCard,
			ArrayList<CCard> lootCardList) {
		this.player = player;
		this.lootedCard = lootedCard;
		this.lootCardList = lootCardList;
	}

	public void execute() {
		player.getHand().add(lootedCard);
		lootCardList.remove(lootedCard);
	}

	public String toString() {
		return (player.getName() + " strips the body of " + lootedCard);
	}
}
