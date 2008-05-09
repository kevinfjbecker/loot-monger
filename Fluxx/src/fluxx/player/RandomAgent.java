package fluxx.player;

import java.util.ArrayList;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.card.action.RotateHands.Direction;

import static fluxx.card.action.RotateHands.Direction.*;

public class RandomAgent implements Agent {

	public Card chooseACard(ArrayList<? extends Card> cards) {
		return cards.get((int) (Math.random() * cards.size()));
	}

	public Player chooseAPlayer(ArrayList<Player> players) {
		return players.get((int) (Math.random() * players.size()));
	}

	public Direction chooseLeftOrRight() {
		return ((int) (Math.random() * 2) == 0 ? Left : Right);
	}

	public boolean chooseYesOrNo() {
		return (Math.random() > 0.25 ? true : false);
	}

	public void setGame(FluxxGame game) {

	}

	public void setPlayer(Player player) {

	}

}
