package fluxx.player;

import java.util.ArrayList;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.card.action.RotateHands.Direction;

public interface Agent {

	Card chooseACard(ArrayList<? extends Card> cards);

	Player chooseAPlayer(ArrayList<Player> players);

	Direction chooseLeftOrRight();

	boolean chooseYesOrNo();

	void setGame(FluxxGame game);

	void setPlayer(Player player);

}
