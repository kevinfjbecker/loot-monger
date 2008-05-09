package fluxx.player;

import java.io.IOException;
import java.util.ArrayList;

import fluxx.card.Card;
import fluxx.FluxxGame;
import fluxx.MenuMachine;
import fluxx.card.Playable;
import fluxx.card.action.RotateHands.Direction;

import static fluxx.card.action.RotateHands.Direction.*;

public class HumanAgent implements Agent {

	public Card chooseACard(ArrayList<? extends Card> cards) {
		System.out.println("Choose a card.");
		String[] cardNames = new String[cards.size()];
		int i = 0;
		for (Playable card : cards)
			cardNames[i++] = card.toString();
		try {
			return cards.get(MenuMachine.solicitMenuChoice(cardNames));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

	public Player chooseAPlayer(ArrayList<Player> players) {
		String[] playerNames = new String[players.size()];
		int i = 0;
		for (Player player : players)
			playerNames[i++] = player.getName();
		try {
			return players.get(MenuMachine.solicitMenuChoice(playerNames));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

	public Direction chooseLeftOrRight() {
		String[] LeftandRight = { Left.toString(), Right.toString() };
		try {
			return (MenuMachine.solicitMenuChoice(LeftandRight) == 0 ? Left
					: Right);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}

	public boolean chooseYesOrNo() {
		String[] yesAndNo = { "Yes", "No" };
		try {
			return (MenuMachine.solicitMenuChoice(yesAndNo) == 0 ? true : false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		return false;
	}

	public void setGame(FluxxGame game) {

	}

	public void setPlayer(Player player) {

	}

}
