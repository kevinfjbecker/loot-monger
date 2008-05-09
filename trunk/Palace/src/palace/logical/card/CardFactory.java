package palace.logical.card;

import static palace.logical.card.LogicalCard.Rank.Ace;
import static palace.logical.card.LogicalCard.Rank.Eight;
import static palace.logical.card.LogicalCard.Rank.Five;
import static palace.logical.card.LogicalCard.Rank.Four;
import static palace.logical.card.LogicalCard.Rank.Jack;
import static palace.logical.card.LogicalCard.Rank.King;
import static palace.logical.card.LogicalCard.Rank.Nine;
import static palace.logical.card.LogicalCard.Rank.Queen;
import static palace.logical.card.LogicalCard.Rank.Seven;
import static palace.logical.card.LogicalCard.Rank.Six;
import static palace.logical.card.LogicalCard.Rank.Ten;
import static palace.logical.card.LogicalCard.Rank.Three;
import static palace.logical.card.LogicalCard.Rank.Two;
import static palace.logical.card.LogicalCard.Suit.Club;
import static palace.logical.card.LogicalCard.Suit.Diamond;
import static palace.logical.card.LogicalCard.Suit.Heart;
import static palace.logical.card.LogicalCard.Suit.Spade;
import palace.logical.card.LogicalCard.Rank;
import palace.logical.card.LogicalCard.Suit;

public abstract class CardFactory {

	public static void populate(LogicalDeck logicalDeck) {

		Rank[] ranks = { Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten,
				Jack, Queen, King, Ace };

		Suit[] suits = { Club, Diamond, Heart, Spade };

		for (Rank rank : ranks)
			for (Suit suit : suits)
				logicalDeck.add(new LogicalCard(suit, rank));

		logicalDeck.shuffle();

	}

}
