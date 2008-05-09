import fluxx.FluxxGame;

public class CheckingCardTotals {

	public static void main(String... args) {

		FluxxGame game = new FluxxGame(null);
		game.setUpRandomTestPlayers();

		System.out.println(game.getCardTotal()
				+ " cards at the start of the game:\n"
				+ game.showCardTotalBreakdown());

		game.play();

		System.out.println("\n" + game.getCardTotal()
				+ " cards at the end of the game:\n"
				+ game.showCardTotalBreakdown());
	}

}
