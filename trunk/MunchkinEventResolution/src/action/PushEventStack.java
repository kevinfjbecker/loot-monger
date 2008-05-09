package action;

import event.Event;
import event.handler.EventStack;

public class PushEventStack implements Action {

	private Event _event;

	private EventStack _eventStack;

	public PushEventStack(EventStack eventStack, Event event) {
		_eventStack = eventStack;
		_event = event;
	}

	public void execute() {
		_eventStack.push(_event);
	}
	
	public String toString(){
		return "PushEventStack("+_event+")";
	}

	public void undo() {
		_eventStack.pop();
	}

}
