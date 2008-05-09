package event.handler;

import event.Event;
import action.PushEventStack;
import action.handler.ActionHandler;

public class EventHandler {

	private ActionHandler _actionHandler;

	private EventStack _eventStack;

	public void handle(Event event) {
		_actionHandler.handle(new PushEventStack(_eventStack, event));
	}

	public void setActionHandler(ActionHandler actionHandler) {
		_actionHandler = actionHandler;
	}

	public void setEventStack(EventStack eventStack) {
		_eventStack = eventStack;
	}

}
