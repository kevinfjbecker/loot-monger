import fluxx.FluxxGame;

public class FluxxGoalDistribution {

	public static void main(String... args) {

		FluxxGame game = new FluxxGame(null);
		game.setUpRandomTestPlayers();

		for (int i = 0; i < 5000; i++) {

			game.play();

			game = new FluxxGame(null);
			game.setUpRandomTestPlayers();
		}

		System.out.println(game.showEndingGoalDisributionOverGamesPlayed());
	}

}
