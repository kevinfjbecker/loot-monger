package event.handler;

import event.Event;
import gameobject.Player;
import action.Acknowledge;
import action.Action;
import action.PopEventStack;
import action.handler.ActionHandler;

public class EventMediator {

	private ActionHandler _actionHandler;

	private EventNotifier _eventNotifier;

	private EventStack _eventStack;

	private Player _player;

	public void mediateEvent() {
		Action action = _player.respond(_eventStack.top());

		_actionHandler.handle(action);
		if (action instanceof Acknowledge) {
			Event event = _eventStack.top();
			_actionHandler.handle(new PopEventStack(_eventStack, event));
			_eventNotifier.notifyListeners(event);
		}
	}

	public void setActionHandler(ActionHandler actionHandler) {
		_actionHandler = actionHandler;
	}

	public void setEventNotifier(EventNotifier eventNotifier) {
		_eventNotifier = eventNotifier;
	}

	public void setEventStack(EventStack eventStack) {
		_eventStack = eventStack;
	}

	public void setPlayer(Player player) {
		_player = player;
	}

}
