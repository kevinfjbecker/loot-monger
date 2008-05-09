package munchkin.card.terasure.item;

import munchkin.card.dungeon.monster.Types;
import munchkin.card.strategy.implementation.AStrategyDecorator;
import munchkin.card.strategy.implementation.CStrategyContext;
import munchkin.card.strategy.implementation.DiceRollModifier;
import munchkin.character.Character;

public class BootsOfRunningReallyFast extends ItemCard implements Footgear {

	CStrategyContext strategies;

	public BootsOfRunningReallyFast() {
		super();
		strategies = new CStrategyContext();
		getStrategyContext().addStrategy(new DiceRollModifier(2));
	}

	public CStrategyContext getStrategyContext() {
		return (strategies);
	}

	public int getBonus() {
		return (0);
	}

	public int getValueInGoldPieces() {
		return (400);
	}

	/*
	 * equip/unequip methods overridden to provide decorator linking
	 */
	public void equip(Character character) {
		super.equip(character);

		AStrategyDecorator rollMod = (AStrategyDecorator) getStrategyContext()
				.getStrategy(Types.diceRoll);
		IGetStrategyContext runStrat = (IGetStrategyContext) character
				.getStrategyContext().getStrategy(Types.runAway);
		rollMod.overrideStrategyInContext(runStrat.getStrategyContext());
	}

	public void unequip() {
		((AStrategyDecorator) getStrategyContext().getStrategy(Types.diceRoll))
				.removeFromContext();
		super.unequip();
	}

	public String getCardText() {
		String s = "";
		s += "Boots of Running Really Fast\n";
		s += "Gives you +2 to Run Away.\n";
		s += "Footgear\n";
		s += "400 Gold Pieces\n";
		return (s);
	}
}