package action;

import event.Event;
import event.handler.EventStack;

public class PopEventStack implements Action {

	Event _event;

	EventStack _eventStack;

	public PopEventStack(EventStack eventStack, Event event) {
		_eventStack = eventStack;
		_event = event;
	}

	public void execute() {
		_eventStack.pop();
	}

	public String toString(){
		return "PopEventStack("+_event+")";
	}

	public void undo() {
		_eventStack.push(_event);
	}

}
