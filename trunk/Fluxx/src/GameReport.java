import fluxx.FluxxGame;

public class GameReport {

	public static void main(String... args) {
		
		FluxxGame game = new FluxxGame(System.out);
		
		game.setUpRandomTestPlayers();
		
		//game.play();
		
		game.start();
	}

}
