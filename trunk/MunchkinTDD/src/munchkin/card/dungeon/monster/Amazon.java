package munchkin.card.dungeon.monster;

import munchkin.card.TreasureCard;
import munchkin.card.strategy.AlternativeConflictResolution;
import munchkin.card.strategy.GivesTreasure;
import munchkin.card.strategy.implementation.BasicGetLevelStrategy;
import munchkin.card.strategy.implementation.BasicGetTreasuresStrategy;
import munchkin.character.charcterclass.NoClass;
import munchkin.character.sex.Male;
import munchkin.deck.Deck;
import munchkin.player.Player;

public class Amazon extends MonsterCard implements GivesTreasure,
		AlternativeConflictResolution {

	private Deck<TreasureCard> treasureDeck;

	public Amazon(){
		super();
		getStrategyContext().addStrategy(new BasicGetLevelStrategy(8));
		getStrategyContext().addStrategy(new BasicGetTreasuresStrategy(2));
	}

	public int getPlayerSpecificBonus(Player player) {
		return (0);
	}

	public void doBadStuff(Player player) {
		if (player.isCharacterClass(new NoClass())) {
			player.loseLevels(3);
		} else {
			player.loseCharacterClass();
		}
	}

	public String getCardText() {
		String s = "";
		s += "Amazon\n";
		s += "Level 8\n";
		s += "Will not attack female players or sex-changed male player; just gives them 1 Treasure instead.\n";
		s += "Bad Stuff: You have had your butt kicked by a woman. Your macho munchkin pride it lost. Lose your Class(es). If you already had no Class, lose 3 levels.\n";
		s += "2 Treasures";
		return (s);
	}

	public boolean willAttack(Player player) {
		return (player.isSex(new Male()));
	}

	public void seekAlternativeConflictResolution(Player player) {
		giveTreasure(player);
	}

	public void giveTreasure(Player player) {
		player.drawACard(getTreasureDeck());
	}

	public Deck<TreasureCard> getTreasureDeck() {
		return (treasureDeck);
	}

	public void setTreasureDeck(Deck<TreasureCard> treasureDeck) {
		this.treasureDeck = treasureDeck;
	}

}
