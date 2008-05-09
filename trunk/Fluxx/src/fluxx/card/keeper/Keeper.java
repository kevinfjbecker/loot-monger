package fluxx.card.keeper;

import fluxx.FluxxGame;
import fluxx.card.Card;

public class Keeper extends Card {

	public static final String[] names = { "Bread", "Chocolate", "Cookies",
			"Death", "Dreams", "Love", "Milk", "Money", "Peace", "Sleep",
			"Television", "The Brain", "The Moon", "The Rocket", "The Sun",
			"The Toaster", "Time", "War" };

	public Keeper(String name, FluxxGame game) {
		super(name, game);
	}

	public void play() {
		_player.getKeepersInPlay().add(this);
	}

}
