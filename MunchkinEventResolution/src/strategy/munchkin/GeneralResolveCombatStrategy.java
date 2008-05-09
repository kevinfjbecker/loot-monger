
package strategy.munchkin;

import strategy.AStrategy;
import strategy.IStrategyType;
import event.Defeat;
import event.Event;
import event.Victory;
import gameobject.Monster;
import gameobject.Player;

public class GeneralResolveCombatStrategy extends AStrategy implements IResolveCombat {

	public GeneralResolveCombatStrategy(IStrategyType type) {
		super(type);
	}

	public Event resolveCombat(Player player, Monster monster) {
		if (player.getCombatLevel(monster) > monster.getCombatLevel(player))
			return new Victory();
		return new Defeat();
	}

}
