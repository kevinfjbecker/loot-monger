import action.handler.ActionHandler;
import action.handler.Executor;
import action.handler.History;
import action.handler.OpenActionHandler;
import event.ResolveCombat;
import event.handler.EventHandler;
import event.handler.EventMediator;
import event.handler.EventNotifier;
import event.handler.EventStack;
import gameobject.BlankCard;
import gameobject.CombatMediator;
import gameobject.Monster;
import gameobject.Player;
import gameobject.Warrior;

/*
 *  TODO:
 *  
 *  discarding of Warrior and unhooking the ResolveCombat strategy
 *  
 *  end of combat and time out of berserk
 *  end of combat and teardown of the combat mediator
 *  combat event and the setup of the combat mediator
 *
 */
public class CombatEventTest implements Runnable {

	private EventHandler _eventHandler;

	private EventStack _eventStack;

	private EventNotifier _eventNotifier;

	private EventMediator _eventMediator;

	private ActionHandler _actionHandler;

	private OpenActionHandler _openActionHandler;

	private Executor _executor;

	private History _history;

	private Player _player;

	private Monster _monster;

	private CombatMediator _combatMediator;

	public CombatEventTest() {

		instantiate();

		initialize();
	}

	private void instantiate() {
		_actionHandler = new ActionHandler();
		_combatMediator = new CombatMediator();
		_eventHandler = new EventHandler();
		_eventMediator = new EventMediator();
		_eventNotifier = new EventNotifier();
		_eventStack = new EventStack();
		_executor = new Executor();
		_history = new History();
		_monster = new Monster();
		_openActionHandler = new OpenActionHandler();
		_player = new Player();
	}

	private void initialize() {

		_eventHandler.setEventStack(_eventStack);
		_eventHandler.setActionHandler(_actionHandler);

		_eventMediator.setEventStack(_eventStack);
		_eventMediator.setEventNotifier(_eventNotifier);
		_eventMediator.setActionHandler(_actionHandler);
		_eventMediator.setPlayer(_player);

		_eventNotifier.addListener(_combatMediator);

		_combatMediator.setPlayer(_player);
		_combatMediator.setMonster(_monster);
		_combatMediator.setEventHandler(_eventHandler);

		_actionHandler.setExecutor(_executor);
		_actionHandler.setOpenActionHandler(_openActionHandler);

		_openActionHandler.setExecutor(_executor);

		_executor.setHistory(_history);

	}

	public void run() {
		_history.commit();
		for (int q = 0; q < 8; q++) {

			instantiate();
			initialize();

			String s1 = "";
			String s2 = "";
			String s3 = "";
			_player.setCharacterClass(new Warrior());
			_player.addToHand(new BlankCard());
			_eventHandler.handle(new ResolveCombat());
			while (!_eventStack.isEmpty())
				_eventMediator.mediateEvent();
			s1 += output();

			_history.rollBack(q);
			_history.commit();
			s3 = output() + "\n";

			while (!_eventStack.isEmpty())
				_eventMediator.mediateEvent();
			s2 += output();

			// System.out.print(s1);
			// System.out.println("\n" + dashline() + "\n");
			// System.out.print(s2);

			System.out.println((s1.equals(s2) ? "pass" : "fail"));
			if (!s1.equals(s2)) {
				System.out.print(s3);
				System.out.print(s2);
			}
			// System.out.println(findMismatchLocation(s1, s2));
		}
	}

	private String output() {
		String s = "";
		s += dashline() + "\n";
		s += _player + "\n";
		s += _eventStack + "\n";
		s += _history + "\n";
		s += dashline() + "\n";
		return (s);
	}

	private String dashline() {
		String s = "";
		for (int i = 0; i < 80; i++)
			s += '-';
		return s;
	}

	public static void main(String... args) {
		CombatEventTest cet = new CombatEventTest();
		cet.run();
	}
}
