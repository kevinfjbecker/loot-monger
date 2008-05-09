import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import fluxx.FluxxGame;
import fluxx.card.Card;
import fluxx.card.action.Action;
import fluxx.card.goal.Goal;
import fluxx.card.keeper.Keeper;

public class CardList {

	public static void main(String... args) {

		FluxxGame game = new FluxxGame(null);

		ArrayList<Card> cards = game.getDeck().getCards();

		alphabetize(cards);

		sysOut(cards);
		
		System.out.println();

		sysOutByType(cards);
	}

	private static void sysOutByType(ArrayList<Card> cards) {
		ArrayList<Card> actions = new ArrayList<Card>();
		ArrayList<Card> goals = new ArrayList<Card>();
		ArrayList<Card> keepers = new ArrayList<Card>();
		ArrayList<Card> newRules = new ArrayList<Card>();

		for (Card card : cards) {
			if (card instanceof Action) {
				actions.add(card);
			} else if (card instanceof Goal) {
				goals.add(card);
			} else if (card instanceof Keeper) {
				keepers.add(card);
			} else { // card instanceof NewRule
				newRules.add(card);
			}
		}

		ArrayList<ArrayList<Card>> lists = new ArrayList<ArrayList<Card>>();
		lists.add(actions);
		lists.add(goals);
		lists.add(keepers);
		lists.add(newRules);

		System.out.printf("%-24s%-24s%-24s%-24s\n\n", "Actions", "Goals", "Keepers", "New Rules");

		int lengthOfLongestList = Math.max(Math.max(actions.size(), goals
				.size()), Math.max(keepers.size(), newRules.size()));

		for (int i = 0; i < lengthOfLongestList; i++) {
			for (ArrayList<Card> list : lists)
				if (i < list.size())
					System.out.printf("%-24s", list.get(i).getName());
				else
					System.out.printf("%-24s", "");
			System.out.println();
		}
	}

	private static void alphabetize(ArrayList<Card> cards) {

		Collections.sort(cards, new Comparator<Card>() {
			public int compare(Card c1, Card c2) {
				return c1.getName().compareTo(c2.getName());
			}
		});

	}

	private static void sysOut(ArrayList<Card> cards) {
		System.out.println("A list of the cards implemented thus far:\n");

		for (Card card : cards)
			System.out.println(card);
	}
}
