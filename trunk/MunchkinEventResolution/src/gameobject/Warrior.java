package gameobject;

import static strategy.munchkin.StrategyTypes.ResolveCombat;
import event.Event;
import event.ResolveCombat;

import java.util.ArrayList;

import strategy.munchkin.WarriorResolveCombatStrategy;
import action.Action;
import action.Berserk;

public class Warrior implements CharacterClass {

	private Player _player;

	private WarriorResolveCombatStrategy _decorator;

	public Warrior() {
		_decorator = new WarriorResolveCombatStrategy(ResolveCombat);
	}

	public ArrayList<Action> getOptions(Event event) {
		ArrayList<Action> options = new ArrayList<Action>();
		if (event instanceof ResolveCombat && _player.hasCards())
			options.add(new Berserk(_player));
			return options;
	}

	public void setPlayer(Player player) {
		_player = player;
		player.acceptStrategyDecorator(_decorator);
	}
	
	public String toString(){
		return "Warrior";
	}

}
