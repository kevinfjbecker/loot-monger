package game;

import player.Player;

public abstract class Rules {
	
	private static final int WINNING_LEVEL = 10;

	public static int minimumOfOne(int i) {
		if( i < 1 ){
			System.out.println("General Rule: \"Minimum of One\" invoked");
			return(1);
		}
		return (i);
	}
	public static int discardNumberOrWholeHand(int numberToDiscard, int handSize){
		if(numberToDiscard > handSize) {
			System.out.println("General Rule: \"Discard Number or Whole Hand\" invoked");
			return(handSize);
		}
		return (numberToDiscard);
	}
	
	public static boolean checkIfPlayerHasWonGame(Player player) {
		if (player.getLevel() >= WINNING_LEVEL)
			return (true);
		return (false);
	}
}
