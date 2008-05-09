import fluxx.FluxxGame;
import fluxx.player.RandomAgent;

public class SolitaireTest {

	public static void main(String... args) {

		FluxxGame game = new FluxxGame(null);
		game.addPlayer("Daddy-O", new RandomAgent());

		for (int i = 0; i < 5000; i++) {
			
			game.play();
			
			game = new FluxxGame(null);
			game.addPlayer("Daddy-O", new RandomAgent());
		}
		
		System.out.println(game.showEndingGoalDisributionOverGamesPlayed());
	}
	
}
