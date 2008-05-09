import game.Game;

public class Driver {
	public static void main(String[] args) {

		String[] playerNameList = { 
				"Brother Zark",
				"Amazona",
				"Lunpin Lightfingers",
				"Laeoretha",
				"TopDeck",
				"Elrond Hubbard" };
		
		String[] agentNameList = {
				"Ken",
				"Kayleigh",
				"Carson",
				"Gilly",
				"Igor",
				"Matt"
		};
		
		Game game = new Game();

		for(int i = 0 ; i < playerNameList.length ; i++)
			game.addPlayer(playerNameList[i],agentNameList[i]);
		
		game.play();
	}
}
