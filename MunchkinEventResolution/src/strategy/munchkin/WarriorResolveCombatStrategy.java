
package strategy.munchkin;

import event.Defeat;
import event.Event;
import event.Victory;
import gameobject.Monster;
import gameobject.Player;
import strategy.AStrategyDecorator;
import strategy.IStrategyType;

public class WarriorResolveCombatStrategy extends AStrategyDecorator implements
		IResolveCombat {

	public WarriorResolveCombatStrategy(IStrategyType type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public Event resolveCombat(Player player, Monster monster) {
		if (player.getCombatLevel(monster) >= monster.getCombatLevel(player))
			return new Victory();
		return new Defeat();
	}

}
