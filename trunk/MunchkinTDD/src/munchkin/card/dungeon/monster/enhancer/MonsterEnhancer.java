package munchkin.card.dungeon.monster.enhancer;

import munchkin.card.DungeonCard;
import munchkin.card.dungeon.monster.MonsterCard;
import munchkin.card.dungeon.monster.Types;
import munchkin.card.strategy.PlayDuringCombat;
import munchkin.card.strategy.implementation.AStrategyDecorator;
import munchkin.card.strategy.implementation.CStrategyContext;

public abstract class MonsterEnhancer extends DungeonCard implements
		PlayDuringCombat {
	
	public MonsterEnhancer() {
		super();
		strategies = new CStrategyContext();
	}

	/*
	 * fields
	 */
	protected MonsterCard monsterCard;
	
	private CStrategyContext strategies;

	/*
	 * accessors
	 */
	protected MonsterCard getMonsterCard() {
		return monsterCard;
	}
	
	public CStrategyContext getStrategyContext(){
		return(strategies);
	}

	/*
	 * mutators
	 */
	protected void setMonsterCard(MonsterCard monsterCard) {
		this.monsterCard = monsterCard;
	}

	/*
	 * decorator linking methods
	 */
	public void attachToMonsterCard(MonsterCard monsterCard) {

		setMonsterCard(monsterCard);
		monsterCard.attachCard(this);

		((AStrategyDecorator) strategies.getStrategy(Types.getTreasures))
				.overrideStrategyInContext(monsterCard.getStrategyContext());

		((AStrategyDecorator) strategies.getStrategy(Types.getLevel))
				.overrideStrategyInContext(monsterCard.getStrategyContext());
	}

	public void detachFromCard(MonsterCard monsterCard) {

		((AStrategyDecorator) strategies.getStrategy(Types.getTreasures))
				.removeFromContext();

		((AStrategyDecorator) strategies.getStrategy(Types.getLevel))
				.removeFromContext();

		getMonsterCard().detachCard(this);
		setMonsterCard(null);
	}
}
