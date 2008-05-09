package strategy.munchkin;

import event.Event;
import gameobject.Monster;
import gameobject.Player;

public interface IResolveCombat {

	Event resolveCombat(Player player, Monster monster);

}
