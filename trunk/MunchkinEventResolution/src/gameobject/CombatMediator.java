package gameobject;

import strategy.munchkin.IResolveCombat;
import event.Event;
import event.ResolveCombat;
import event.handler.EventHandler;
import event.handler.EventListener;

public class CombatMediator implements EventListener {

	private EventHandler _eventHandler;

	private Monster _monster;

	private Player _player;

	private IResolveCombat _resolveCombatStrategy;

	public void onEvent(Event event) {
		if (event instanceof ResolveCombat) {
			_eventHandler.handle(resolveCombat());
		}
	}

	private Event resolveCombat() {
		setResovleCombatStrategy();
		return _resolveCombatStrategy.resolveCombat(_player,_monster);
	}

	public void setEventHandler(EventHandler eventHandler) {
		_eventHandler = eventHandler;
	}

	public void setMonster(Monster monster) {
		_monster = monster;
	}

	public void setPlayer(Player player) {
		_player = player;
	}

	private void setResovleCombatStrategy() {
		_resolveCombatStrategy = _player.getResolveCombatStrategy();
	}

}
