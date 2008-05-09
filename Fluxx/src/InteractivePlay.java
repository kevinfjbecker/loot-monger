import fluxx.FluxxGame;
import fluxx.player.HumanAgent;
import fluxx.player.RandomAgent;

public class InteractivePlay {

	public static void main(String... args) {

		FluxxGame game = new FluxxGame(System.out);

		game.addPlayer("Meat Bag", new HumanAgent());
		
		for (int i = 0; i < 5; i++)
			game.addPlayer("Player" + i, new RandomAgent());

		game.play();
	}

}
