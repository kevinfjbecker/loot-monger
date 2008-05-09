package fluxx.card.goal;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.player.Player;

public abstract class Goal extends Card {

	public static final String[] names = { "Baked Goods", "Bed Time",
			"Chocolate Cookies", "Chocolate Milk", "Death by Chocolate",
			"Dreamland", "Hearts and Minds", "Hippyism", "Milk and Cookies",
			"Night and Day", "Rocket Science", "Rocket to the Moon",
			"Squishy Chocolate", "The Appliances", "Time is Money", "Toast",
			"War = Death", "Winning the Lottery" };

	public Goal(String name, FluxxGame game) {
		super(name, game);
	}

	public abstract Player checkForWinner();

	public void play() {
		setPlayer(null);
		if (_game.getGoal() != null)
			_game.getGoal().discard();
		_game.setGoal(this);
	}

}
