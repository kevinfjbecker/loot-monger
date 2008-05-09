import fluxx.FluxxGame;

public class CountingCardsDrawnAndPlayed {

	public static void main(String... args) {
		
		FluxxGame game = new FluxxGame(null);
		game.setUpRandomTestPlayers();
		
		game.play();
		
		System.out.println(game.showCardsDrawnAndPlayedPerTurn());
	}
}
