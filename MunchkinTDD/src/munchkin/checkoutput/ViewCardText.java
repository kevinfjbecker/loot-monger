package munchkin.checkoutput;

import java.util.ArrayList;

import munchkin.card.Card;
import munchkin.card.dungeon.monster.Amazon;
import munchkin.card.dungeon.monster.Bigfoot;
import munchkin.card.dungeon.monster.OneThousandEightHundredEtcOrcs;
import munchkin.card.dungeon.monster.enhancer.Ancient;
import munchkin.card.dungeon.monster.enhancer.Baby;
import munchkin.card.terasure.goupalevel.BoilAnAntHill;
import munchkin.card.terasure.goupalevel.OneThousandGoldPieces;
import munchkin.card.terasure.item.BadAssBandana;
import munchkin.card.terasure.item.BootsOfButtKicking;
import munchkin.card.terasure.item.BootsOfRunningReallyFast;

public class ViewCardText {

	public static void main(String[] args) {
		ArrayList<Card> cardList = new ArrayList<Card>();
		cardList.add(new OneThousandGoldPieces());
		cardList.add(new OneThousandEightHundredEtcOrcs());
		cardList.add(new Amazon());
		cardList.add(new Ancient());
		cardList.add(new Baby());
		cardList.add(new BadAssBandana());
		cardList.add(new Bigfoot());
		cardList.add(new BoilAnAntHill());
		cardList.add(new BootsOfButtKicking());
		cardList.add(new BootsOfRunningReallyFast());
		System.out.println("\nMunchkin\n");
		for( int i = 0 ; i < cardList.size() ; i++)
			System.out.println(cardList.get(i).getCardText()+"\n");
	}

}
