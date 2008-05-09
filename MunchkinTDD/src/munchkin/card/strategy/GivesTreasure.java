package munchkin.card.strategy;

import munchkin.card.TreasureCard;
import munchkin.deck.Deck;
import munchkin.player.Player;

public interface GivesTreasure {
	public abstract void giveTreasure(Player player);
	public abstract Deck<TreasureCard> getTreasureDeck();
	public abstract void setTreasureDeck(Deck<TreasureCard> treasureDeck);
}
